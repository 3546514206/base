/// <reference path = "../IShape/IShape.ts" />
namespace CircleNS {
    import IShape = IShapeNS.IShape;

    export class Circle implements IShape {
        public draw() {
            console.log("Circle is drawn");
        }
    }
}