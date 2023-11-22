package decorator

import "fmt"

func ExampleDecorator() {
	var c Component = &ConcreteComponent{}
	c = WrapAddDecorator(c, 10)
	c = WrapMulDecorator(c, 8)
	res := c.Calc()

	fmt.Printf("res %d\n", res)
	// Output:
	// res 80
}
