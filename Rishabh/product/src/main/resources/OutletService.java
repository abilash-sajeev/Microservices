package com.example.outlet;

public class OutletService {
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/GetProducts")
	public List<ProductData> getProducts(){
		Product[] productsArray = restTemplate.getForObject("http://localhost:8081/GetProductInfo", Product[].class);
		return Arrays.asList(productsArray).stream()
				.map(product -> new ProductData(product.getId(), product.getProductName(), product.getPrice(), "Unsold"))
				.collect(Collectors.toList());
	}
	
	@RequestMapping("/GetProducts/{id}")
	public ResponseEntity<ProductData> getProduct(@PathVariable String id) {
		try {
			Product product = restTemplate.getForObject("http://localhost:8081/GetProductInfo/"+id, Product.class);
			return new ResponseEntity<>(new ProductData(product.getId(), product.getproductName(), product.getPrice(), "Unsold"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new ProductNotFoundException().toString(), HttpStatus.NOT_FOUND);
		}
	}


}
