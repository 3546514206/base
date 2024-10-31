/// <reference path = "../IShape/IShape.ts" />
namespace ParallelogramNS {
    import IShape = IShapeNS.IShape;

    export class Parallelogram implements IShape {
        public draw() {
            console.log("Circle is drawn");
        }
    }
}