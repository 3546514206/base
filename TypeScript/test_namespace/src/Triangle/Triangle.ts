namespace TriangleNS {
    import IShape = IShapeNS.IShape;

    export class Triangle implements IShape {
        public draw() {
            console.log("Triangle is drawn");
        }
    }
}