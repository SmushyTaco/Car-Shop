package com.smushytaco.car_shop.bootstrap;
import com.smushytaco.car_shop.domain.InHousePart;
import com.smushytaco.car_shop.domain.OutsourcedPart;
import com.smushytaco.car_shop.domain.Product;
import com.smushytaco.car_shop.service.InHousePartService;
import com.smushytaco.car_shop.service.OutsourcedPartService;
import com.smushytaco.car_shop.service.PartService;
import com.smushytaco.car_shop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
@Slf4j
@Component
public class BootstrapData implements CommandLineRunner {
    private static final String DIVIDER = "========================================";
    private static final String COUNT = "Count: {}";
    private final PartService partService;
    private final ProductService productService;
    private final OutsourcedPartService outsourcedPartService;
    private final InHousePartService inHousePartService;
    public BootstrapData(final PartService partService, final ProductService productService, final OutsourcedPartService outsourcedPartService, final InHousePartService inHousePartService) {
        this.partService = partService;
        this.productService = productService;
        this.outsourcedPartService = outsourcedPartService;
        this.inHousePartService = inHousePartService;
    }
    @Override
    public void run(final String... args) {
        if (partService.findAll().isEmpty() && productService.findAll().isEmpty()) {
            OutsourcedPart electricEnginePart = outsourcedPartService.save(new OutsourcedPart("Electric Engine", 100.0, 50, 25, 75, "Tesla"));
            Product voltSurge = productService.save(new Product("Volt Surge", 250.0, 50));
            productService.associatePartToProduct(voltSurge.getId(), electricEnginePart.getId());

            OutsourcedPart autopilotPart = outsourcedPartService.save(new OutsourcedPart("Autopilot", 300.0, 50, 25, 75, "Tesla"));
            Product autoNavigator = productService.save(new Product("AutoNavigator", 450.0, 50));
            productService.associatePartToProduct(autoNavigator.getId(), autopilotPart.getId());

            OutsourcedPart bulletProofGlassPart = outsourcedPartService.save(new OutsourcedPart("Bullet Proof Glass", 150.0, 50, 25, 75, "Armour Max"));
            Product fortressGlide = productService.save(new Product("Fortress Glide", 400.0, 50));
            productService.associatePartToProduct(fortressGlide.getId(), bulletProofGlassPart.getId());

            InHousePart spoilerPart = inHousePartService.save(new InHousePart("Spoiler", 75.0, 50, 25, 75, 1));
            Product aeroSwift = productService.save(new Product("Aero Swift", 150.0, 50));
            productService.associatePartToProduct(aeroSwift.getId(), spoilerPart.getId());

            InHousePart goldRimsPart = inHousePartService.save(new InHousePart("Gold Rims", 1000.0, 50, 25, 75, 2));
            Product goldenHaloCruiser = productService.save(new Product("Golden Halo Cruiser", 1500.0, 50));
            productService.associatePartToProduct(goldenHaloCruiser.getId(), goldRimsPart.getId());
        }
        log.info(DIVIDER);
        log.info("Outsourced Parts:");
        List<OutsourcedPart> outsourcedParts = outsourcedPartService.findAll();
        log.info(COUNT, outsourcedParts.size());
        for(OutsourcedPart outsourcedPart : outsourcedParts) log.info("Name: \"{}\" Company Name: \"{}\"", outsourcedPart.getName(), outsourcedPart.getCompanyName());
        log.info(DIVIDER);
        log.info("In House Parts:");
        List<InHousePart> inHouseParts = inHousePartService.findAll();
        log.info(COUNT, inHouseParts.size());
        for(InHousePart inHousePart : inHouseParts) log.info("Name: \"{}\" Part ID: \"{}\"", inHousePart.getName(), inHousePart.getPartId());
        log.info(DIVIDER);
        log.info("Products:");
        List<Product> products = productService.findAll();
        log.info(COUNT, products.size());
        for(Product product : products) log.info("Name: \"{}\"", product.getName());
        log.info(DIVIDER);
    }
}