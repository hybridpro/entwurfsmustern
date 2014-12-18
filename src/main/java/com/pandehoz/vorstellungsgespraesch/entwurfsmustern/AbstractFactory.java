package com.pandehoz.vorstellungsgespraesch.entwurfsmustern;

import groovyjarjarcommonscli.BasicParser;
import groovyjarjarcommonscli.CommandLine;
import groovyjarjarcommonscli.CommandLineParser;
import groovyjarjarcommonscli.HelpFormatter;
import groovyjarjarcommonscli.Options;
import groovyjarjarcommonscli.ParseException;


interface Factory{
	public Product createProduct();
}

interface Product{
	public void setupProduct();
	
	public void tearDownProduct();
}

class ImportProductFactory implements Factory{

	public Product createProduct() {	
		return new ImportProduct();
	}
	
}

class ExportProductFactory implements Factory{

	public Product createProduct() {	
		return new ExportProduct();
	}
	
}

class ExportProduct implements Product{
	
	public void setupProduct() {
		System.out.println("ExportProduct - setup");
	}

	public void tearDownProduct() {
		System.out.println("ExportProduct - teardown");
		
	}
}

class ImportProduct implements Product{

	public void setupProduct() {
		System.out.println("ImportProduct - setup");
	}

	public void tearDownProduct() {
		System.out.println("ImportProduct - teardown");
		
	}
}



public class AbstractFactory 
{
    public static void main( String[] args )
    {
        CommandLineParser parser = new BasicParser();
        
        Options options = new Options();
        options.addOption("p", "product", true, "Specifies the type of product to create.\nAccepted values are import and export");
        options.addOption("h", "help", false, "Shows usage");
        
        CommandLine cmd = null;
        Factory factory = null;
        try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("p")) {
				
				String typeOfProduct = cmd.getOptionValue("p");
				
				if (typeOfProduct.equalsIgnoreCase("import")) {
					factory = new ImportProductFactory();
				} else if(typeOfProduct.equalsIgnoreCase("export")){
					factory = new ExportProductFactory();
				} else {
					HelpFormatter formater = new HelpFormatter();
					formater.printHelp("Main", options);
					System.exit(0);
				}
				
			} else {
				System.out.println("Please specify the type of product to create");
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        factory.createProduct().setupProduct();
        factory.createProduct().tearDownProduct();
    }
}
