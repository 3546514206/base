import Circle = CircleNS.Circle;

function drawAllShapes(shape: IShapeNS.IShape) {
    shape.draw();
}

drawAllShapes(new Circle());
drawAllShapes(new TriangleNS.Triangle());