package gerger;

import java.util.List;

public class Test {
	
	

}

class Company {
	String name;
	List<Product> products;
}

class Product {
	
	String name;
	String desc;

	public Product(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
