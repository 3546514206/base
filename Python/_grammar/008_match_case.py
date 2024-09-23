def classify_shape(shape):
    match shape:
        case {"type": "circle", "radius": radius} if radius > 0:
            return "Circle with radius {radius}"
        case {"type": "rectangle", "width": w, "height": h}:
            return "Rectangle with width {w} and height {h}"
        case _:
            return "Unknown shape"


def main():
    shape1 = {"type": "circle", "radius": 5}
    shape2 = {"type": "rectangle", "width": 10, "height": 20}
    # 输出: Circle with radius 5
    print(classify_shape(shape1))
    # 输出: Rectangle with width 10 and height 20
    print(classify_shape(shape2))
    classify_shape(shape2)


if __name__ == "__main__":
    main()
