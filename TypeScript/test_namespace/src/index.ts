function drawAllShapes(shape: Drawing.IShape) {
    shape.draw();
}

drawAllShapes(new Drawing.Circle());
drawAllShapes(new Drawing.Triangle());