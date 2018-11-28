package com.sample.compiler.poc;

import org.joor.Reflect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Supplier;

@SpringBootApplication
public class PocApplication {

	public static void main(String[] args) {

		Supplier<String> supplier = Reflect.compile(
				"com.sample.compiler.poc.RuntimeCompilerTest",
				"package com.sample.compiler.poc; " +
						"import com.sample.compiler.poc.bo.SampleBO; " +
						"class RuntimeCompilerTest implements java.util.function.Supplier<String> { " +
						"public String get() { " +
						"SampleBO sample = new SampleBO();" +
						"System.out.println(sample.getClass());" +
						"return \"Hello World!\"; } " +
						"}",
				Thread.currentThread().getContextClassLoader()).create().get();

		System.out.println("----------------------------- REFLECT -----------------------------");
		System.out.println(supplier.getClass() + " - " + supplier.get());
		System.out.println("-------------------------------------------------------------------");

		SpringApplication.run(PocApplication.class, args);
	}
}
