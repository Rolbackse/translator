package test;

import impl.Keyword;

import java.util.Map;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import converter.Converter;
import exception.UnknownValueException;

@RunWith(Parameterized.class)
public abstract class ConverterTest<T extends Converter<?>, R> {

	protected String input;
	protected Map<Keyword, Object> anteriorValues;
	protected T converter;
	protected R result;

	public ConverterTest(String input, Map<Keyword, Object> options) throws UnknownValueException {
		this.input = input;
		this.anteriorValues = options;
	}

}
