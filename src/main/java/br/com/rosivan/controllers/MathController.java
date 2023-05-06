package br.com.rosivan.controllers;

import br.com.rosivan.converters.NumberConverter;
import br.com.rosivan.exceptions.UnsupportedMathOperationException;
import br.com.rosivan.math.SimpleMath;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;




@RestController
public class MathController {
    private final AtomicLong counter = new AtomicLong();
    private SimpleMath math = new SimpleMath();


    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
                method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        final double resultSum = math.sum(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
        return resultSum;
    }

    @RequestMapping(value = "/subtration/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double subtration(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        final double result = math.subtration(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
        return result;
    }

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double multiplication(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        final double result = math.multiplication(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
        return result;
    }


    @RequestMapping(value = "/division/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double division(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        final double result = math.division(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
        return result;
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double mean(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        final double result = math.mean(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
        return result;
    }

    @RequestMapping(value = "/squareRoot/{number}",
            method = RequestMethod.GET)
    public Double squareRoot(
            @PathVariable(value = "number") String number
    ) throws Exception {
        if(!NumberConverter.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        final double result = math.squareRoot(NumberConverter.converToDouble(number));
        return result;
    }
}
