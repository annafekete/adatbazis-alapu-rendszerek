document.addEventListener('DOMContentLoaded', function() {
    // DOM elemek elérése
    const uploadArea = document.getElementById('upload-area');
    const uploadBtn = document.getElementById('upload-btn');
    const videoUpload = document.getElementById('video-upload');
    const previewSection = document.getElementById('preview-section');
    const burningOptions = document.getElementById('burning-options');
    const videoPreview = document.getElementById('video-preview');
    const applyPreviewBtn = document.getElementById('apply-preview');
    const processVideoBtn = document.getElementById('process-video');
    const processingSection = document.getElementById('processing-section');
    const resultSection = document.getElementById('result-section');
    const resultVideo = document.getElementById('result-video');
    const progressBar = document.getElementById('progress-bar');
    const progressText = document.getElementById('progress-text');
    const downloadBtn = document.getElementById('download-video');
    const shareBtn = document.getElementById('share-video');

    // Feliratok beállításai
    const textOverlay = document.getElementById('text-overlay');
    const position = document.getElementById('position');
    const fontSize = document.getElementById('font-size');
    const fontSizeValue = document.getElementById('font-size-value');
    const textColor = document.getElementById('text-color');
    const textOpacity = document.getElementById('text-opacity');
    const textOpacityValue = document.getElementById('text-opacity-value');
    const backgroundColor = document.getElementById('background-color');
    const backgroundOpacity = document.getElementById('background-opacity');
    const backgroundOpacityValue = document.getElementById('background-opacity-value');
    const outputFormat = document.getElementById('output-format');
    const outputQuality = document.getElementById('output-quality');

    // Videó objektum
    let videoFile = null;
    let videoUrl = null;
    let resultVideoUrl = null;
    let videoCanvas = null;
    let videoContext = null;

    // Canvas overlay a szöveg beégetéséhez
    let overlayCanvas = document.createElement('canvas');
    let overlayContext = overlayCanvas.getContext('2d');

    // Eseménykezelők beállítása
    uploadArea.addEventListener('click', () => videoUpload.click());
    uploadBtn.addEventListener('click', () => videoUpload.click());

    // Drag and drop funkcionalitás
    uploadArea.addEventListener('dragover', (e) => {
        e.preventDefault();
        uploadArea.style.backgroundColor = '#f0f7fd';
    });

    uploadArea.addEventListener('dragleave', () => {
        uploadArea.style.backgroundColor = '';
    });

    uploadArea.addEventListener('drop', (e) => {
        e.preventDefault();
        uploadArea.style.backgroundColor = '';
        handleFileUpload(e.dataTransfer.files);
    });

    // Fájl kiválasztás
    videoUpload.addEventListener('change', (e) => {
        handleFileUpload(e.target.files);
    });

    // Csúszkák értékeinek kijelzése
    fontSize.addEventListener('input', () => {
        fontSizeValue.textContent = `${fontSize.value}px`;
    });

    textOpacity.addEventListener('input', () => {
        const percentage = Math.round(textOpacity.value * 100);
        textOpacityValue.textContent = `${percentage}%`;
    });

    backgroundOpacity.addEventListener('input', () => {
        const percentage = Math.round(backgroundOpacity.value * 100);
        backgroundOpacityValue.textContent = `${percentage}%`;
    });

    // Előnézet frissítése gomb
    applyPreviewBtn.addEventListener('click', updatePreview);

    // Videó feldolgozása gomb
    processVideoBtn.addEventListener('click', processVideo);

    // Letöltés gomb
    downloadBtn.addEventListener('click', downloadVideo);

    // Megosztás gomb
    shareBtn.addEventListener('click', shareVideo);

    // Fájl kezelés
    function handleFileUpload(files) {
        if (files.length === 0) return;

        const file = files[0];

        if (!file.type.startsWith('video/')) {
            alert('Kérjük, válasszon videó fájlt!');
            return;
        }

        videoFile = file;
        videoUrl = URL.createObjectURL(file);

        // Előnézet beállítása
        videoPreview.src = videoUrl;
        videoPreview.addEventListener('loadedmetadata', () => {
            previewSection.classList.remove('hidden');
            burningOptions.classList.remove('hidden');

            // Canvas beállítása
            overlayCanvas.width = videoPreview.videoWidth;
            overlayCanvas.height = videoPreview.videoHeight;
        });
    }

    // Előnézet frissítése
    function updatePreview() {
        if (!videoUrl) return;

        // Canvas létrehozása ha még nem létezik
        if (!videoCanvas) {
            videoCanvas = document.createElement('canvas');
            videoContext = videoCanvas.getContext('2d');
        }

        // Canvas méret beállítása
        videoCanvas.width = videoPreview.videoWidth;
        videoCanvas.height = videoPreview.videoHeight;

        // Aktuális képkocka megjelenítése
        videoContext.drawImage(videoPreview, 0, 0, videoCanvas.width, videoCanvas.height);

        // Szöveg beégetése
        drawOverlayText(videoContext);

        // Képkocka mentése
        const frameDataUrl = videoCanvas.toDataURL('image/jpeg');

        // Kép megjelenítése előnézetként
        const img = new Image();
        img.src = frameDataUrl;
        img.onload = () => {
            // Tisztítsuk a vásznat és rajzoljuk újra
            videoContext.clearRect(0, 0, videoCanvas.width, videoCanvas.height);
            videoContext.drawImage(img, 0, 0);

            // Próbáljuk megjeleníteni az eredményt
            videoPreview.pause();
            videoPreview.poster = frameDataUrl;
        };
    }

    // Szöveg rajzolása
    function drawOverlayText(context) {
        const text = textOverlay.value || '© VideoFlow';
        const posValue = position.value;
        const fontSizeValue = fontSize.value;
        const textColorValue = textColor.value;
        const textOpacityValue = textOpacity.value;
        const bgColorValue = backgroundColor.value;
        const bgOpacityValue = backgroundOpacity.value;

        // Szöveg méret mérése
        context.font = `${fontSizeValue}px Arial`;
        const textMetrics = context.measureText(text);
        const textWidth = textMetrics.width;
        const textHeight = parseInt(fontSizeValue, 10);

        // Pozíció számítása
        let x, y;

        switch (posValue) {
            case 'top-left':
                x = 20;
                y = 20 + textHeight;
                break;
            case 'top-right':
                x = context.canvas.width - textWidth - 20;
                y = 20 + textHeight;
                break;
            case 'bottom-left':
                x = 20;
                y = context.canvas.height - 20;
                break;
            case 'bottom-right':
            default:
                x = context.canvas.width - textWidth - 20;
                y = context.canvas.height - 20;
                break;
        }

        // Háttér rajzolása
        const padding = 10;
        context.fillStyle = hexToRgba(bgColorValue, bgOpacityValue);
        context.fillRect(
            x - padding,
            y - textHeight - padding/2,
            textWidth + padding * 2,
            textHeight + padding
        );

        // Szöveg rajzolása
        context.fillStyle = hexToRgba(textColorValue, textOpacityValue);
        context.fillText(text, x, y);
    }

    // Hex színkód átalakítása rgba formátumba
    function hexToRgba(hex, opacity) {
        const r = parseInt(hex.slice(1, 3), 16);
        const g = parseInt(hex.slice(3, 5), 16);
        const b = parseInt(hex.slice(5, 7), 16);

        return `rgba(${r}, ${g}, ${b}, ${opacity})`;
    }

    // Videó feldolgozása
    function processVideo() {
        if (!videoFile) return;

        // UI állapot frissítése
        processingSection.classList.remove('hidden');
        burningOptions.classList.add('hidden');

        // Valós életben itt küldenénk a szervernek a videót és a beállításokat
        // Itt csak szimulálunk egy feldolgozási folyamatot

        let progress = 0;
        const progressInterval = setInterval(() => {
            progress += Math.random() * 5;
            if (progress >= 100) {
                progress = 100;
                clearInterval(progressInterval);
                finishProcessing();
            }

            // Progressbar frissítése
            progressBar.style.width = `${progress}%`;
            progressText.textContent = `${Math.round(progress)}%`;
        }, 500);
    }

    // Feldolgozás befejezése
    function finishProcessing() {
        setTimeout(() => {
            // Eredeti videót használjuk eredményként
            // Valós alkalmazásban a szerver által feldolgozott videót kapnánk vissza
            resultVideoUrl = videoUrl;
            resultVideo.src = resultVideoUrl;

            // UI frissítése
            processingSection.classList.add('hidden');
            resultSection.classList.remove('hidden');
        }, 1000);
    }

    // Videó letöltése
    function downloadVideo() {
        if (!resultVideoUrl) return;

        // Letöltés link létrehozása
        const a = document.createElement('a');
        a.href = resultVideoUrl;
        a.download = `videoflow_${Date.now()}.${outputFormat.value}`;
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
    }

    // Videó megosztása
    function shareVideo() {
        // Itt valós alkalmazásban a megosztási funkciót implementálnánk
        // Web Share API használata, ha elérhető
        if (navigator.share) {
            navigator.share({
                title: 'Az én VideoFlow videóm',
                text: 'Nézd meg ezt a videót, amit a VideoFlow alkalmazásban készítettem!',
                url: window.location.href
            })
                .catch(error => console.log('Megosztás sikertelen', error));
        } else {
            alert('Sajnáljuk, a megosztási funkció nem érhető el a böngészőjében.');
        }
    }
});