#!/bin/bash

echo "Enter first number:"
read a

echo "Enter operator (+ - * /):"
read op

echo "Enter second number:"
read b

case $op in
  +) result=$((a + b));;
  -) result=$((a - b));;
  \*) result=$((a * b));;
  /) result=$((a / b));;
  *) echo "Invalid operator"; exit 1;;
esac

echo "Result = $result"
