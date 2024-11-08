// script.js
const canvas = document.getElementById('drawCanvas');
const ctx = canvas.getContext('2d');

// Ajustamos el tamaño del lienzo para que ocupe el 80% del ancho y 50% del alto de la ventana
canvas.width = window.innerWidth * 0.8;
canvas.height = window.innerHeight * 0.5;

// Variables para el dibujo
let isDrawing = false;      
let lastX = 0;
let lastY = 0;
let brushSize = 5;
let brushColor = '#000000';

// Función para comenzar el dibujo
function startDrawing(e) {
    isDrawing = true;
    [lastX, lastY] = [e.offsetX, e.offsetY];
}

// Función para dibujar sobre el lienzo
function draw(e) {
    if (!isDrawing) return;
    
    ctx.lineWidth = brushSize;
    ctx.lineCap = 'round';
    ctx.strokeStyle = brushColor;

    ctx.beginPath();
    ctx.moveTo(lastX, lastY);
    ctx.lineTo(e.offsetX, e.offsetY);
    ctx.stroke();

    [lastX, lastY] = [e.offsetX, e.offsetY];
}

// Función para dejar de dibujar
function stopDrawing() {
    isDrawing = false;
}

// Limpiar el lienzo
function clearCanvas() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
}

// Eventos para dibujar con el mouse
canvas.addEventListener('mousedown', startDrawing);
canvas.addEventListener('mousemove', draw);
canvas.addEventListener('mouseup', stopDrawing);
canvas.addEventListener('mouseout', stopDrawing);

// Cambiar el color del pincel
const colorPicker = document.getElementById('colorPicker');
colorPicker.addEventListener('input', (e) => {
    brushColor = e.target.value;
});

// Cambiar el tamaño del pincel
const brushSizeSlider = document.getElementById('brushSize');
brushSizeSlider.addEventListener('input', (e) => {
    brushSize = e.target.value;
});

// Botón de borrar
const clearBtn = document.getElementById('clearBtn');
clearBtn.addEventListener('click', clearCanvas);
