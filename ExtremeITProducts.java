/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.myuniven.extremeitproducts;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Shai Tlaisego Queen 23014244
 */
public class ExtremeITProducts {
    public static void main(String[] args) {
        Products.DisplayMenu();
    }
}


 class Products {
    private static ArrayList<ReportData> productList = new ArrayList<ReportData>();
    private static Scanner scanner = new Scanner(System.in);

    // Display Menu 
    public static void DisplayMenu() {
        System.out.println("BRIGHT FUTURE TECHNOLOGIES APPLICATION");
        System.out.println("**************************************");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");
        String startChoice = scanner.nextLine();

        if (!startChoice.equals("1")) {
            ExitApplication();
            return;
        }
//while loop for the menu selection 
        int menuChoice = 0;
        while (menuChoice != 6) {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new product.");
            System.out.println("(2) Search for a product.");
            System.out.println("(3) Update a product.");
            System.out.println("(4) Delete a product.");
            System.out.println("(5) Print report.");
            System.out.println("(6) Exit Application.");
            System.out.print("Enter option: ");

            if (scanner.hasNextInt()) {
                menuChoice = scanner.nextInt();
                scanner.nextLine(); // skip a line

                switch (menuChoice) {
                    case 1:
                        CaptureProduct();
                        break;
                    case 2:
                        SearchProduct();
                        break;
                    case 3:
                        UpdateProduct();
                        break;
                    case 4:
                        DeleteProduct();
                        break;
                    case 5:
                        PrintReport();
                        break;
                    case 6:
                        ExitApplication();
                        break;
                    default:
                        System.out.println("Invalid selection. Please choose between 1 and 6.");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }

            if (menuChoice != 6) {
                System.out.print("\nEnter (1) to launch menu or any other key to exit: ");
                String continueChoice = scanner.nextLine();
                if (!continueChoice.equals("1")) {
                    ExitApplication();
                    break;
                }
            }
        }
    }

    // Capture Product Details
    public static void CaptureProduct() {
        System.out.println("\nCAPTURE A NEW PRODUCT");
        System.out.println("*********************");
        System.out.print("Enter the product code: ");
        String code = scanner.nextLine();

        System.out.print("Enter the product name: ");
        String name = scanner.nextLine();

        // Category selection 
        String category = "";
        boolean validCategory = false;
        while (!validCategory) {
            System.out.println("Select the product category:");
            System.out.println("1. Desktop Computer");
            System.out.println("2. Laptop");
            System.out.println("3. Tablet");
            System.out.println("4. Printer");
            System.out.println("5. Gaming Console");
            System.out.print("Product Category >> ");

            String catInput = scanner.nextLine();
            if (catInput.equals("1")) {
                category = "Desktop Computer";
                validCategory = true;
            } else if (catInput.equals("2")) {
                category = "Laptop";
                validCategory = true;
            } else if (catInput.equals("3")) {
                category = "Tablet";
                validCategory = true;
            } else if (catInput.equals("4")) {
                category = "Printer";
                validCategory = true;
            } else if (catInput.equals("5")) {
                category = "Gaming Console";
                validCategory = true;
            } else {
                System.out.println("Invalid product category selection. Please re-enter a valid product category.");
            }
        }

        // Warranty selection 
        System.out.print("Indicate the product warranty. Enter (1) for 6 months or any other key for 2 years: ");
        String warrantyInput = scanner.nextLine();
        String warranty = warrantyInput.equals("1") ? "6 months" : "2 years";

        System.out.print("Enter the price for " + name + " >> ");
        double price = scanner.nextDouble();

        System.out.print("Enter the stock level for " + name + " >> ");
        int stock = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter the supplier for " + name + " >> ");
        String supplier = scanner.nextLine();

        SaveProduct(code, name, category, warranty, price, stock, supplier);
    }

    // Save Product to memory array list
    public static void SaveProduct(String code, String name, String category, String warranty, double price, int stock, String supplier) {
        ReportData newProduct = new ReportData(code, name, category, warranty, price, stock, supplier);
        productList.add(newProduct);
        System.out.println("Product details has been saved successfully!!!");
    }

    // Search Product
    public static void SearchProduct() {
        System.out.print("Please enter the product code to search: ");
        System.out.println("*****************************************************");
        String code = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < productList.size(); i++) {
            ReportData p = productList.get(i);
            if (p.getProductCode().equalsIgnoreCase(code)) {
                System.out.println("\nPRODUCT SEARCH RESULTS");
                System.out.println("*************************************************");
                System.out.println("PRODUCT CODE:\t\t" + p.getProductCode());
                System.out.println("PRODUCT NAME:\t\t" + p.getProductName());
                System.out.println("PRODUCT CATEGORY:\t" + p.getProductCategory());
                System.out.println("PRODUCT WARRANTY:\t" + p.getProductWarranty());
                System.out.println("PRODUCT PRICE:\t\tR " + p.getProductPrice());
                System.out.println("PRODUCT STOCK LEVELS:\t" + p.getProductStock());
                System.out.println("PRODUCT SUPPLIER:\t" + p.getProductSupplier());
                System.out.println("*************************************************");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("The product cannot be located. Invalid Product");
        }
    }

    // Update Product
    public static void UpdateProduct() {
        System.out.print("Please enter the product code to update: ");
        String code = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < productList.size(); i++) {
            ReportData p = productList.get(i);
            if (p.getProductCode().equalsIgnoreCase(code)) {
                found = true;

                System.out.print("Update the warranty? (y) Yes, (n) No ");
                String updateWarranty = scanner.nextLine();
                if (updateWarranty.equalsIgnoreCase("y")) {
                    System.out.print("Enter new warranty - Enter (1) for 6 months or any other key for 2 years: ");
                    String wInput = scanner.nextLine();
                    p.setProductWarranty(wInput.equals("1") ? "6 months" : "2 years");
                }

                System.out.print("Update the product price? (y) Yes, (n) No ");
                String updatePrice = scanner.nextLine();
                if (updatePrice.equalsIgnoreCase("y")) {
                    System.out.print("Enter the new price for " + p.getProductName() + " >> ");
                    p.setProductPrice(scanner.nextDouble());
                    scanner.nextLine();
                }

                System.out.print("Update the stock level? (y) Yes, (n) No ");
                String updateStock = scanner.nextLine();
                if (updateStock.equalsIgnoreCase("y")) {
                    System.out.print("Enter the new stock level >> ");
                    p.setProductStock(scanner.nextInt());
                    scanner.nextLine();
                }

                System.out.println("Product details has been updated successfully!!!");
                break;
            }
        }

        if (!found) {
            System.out.println("The product cannot be located for update.");
        }
    }

    // Delete Product
    public static void DeleteProduct() {
        System.out.print("Please enter the product code to be deleted: ");
        String code = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < productList.size(); i++) {
            ReportData p = productList.get(i);
            if (p.getProductCode().equalsIgnoreCase(code)) {
                found = true;
                System.out.print("Are you sure you want to delete product " + p.getProductCode() + "? (y) Yes, (n) No: ");
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("y")) {
                    productList.remove(i);
                    System.out.println("Product deleted successfully.");
                } else {
                    System.out.println("Deletion cancelled.");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("The product cannot be located for deletion.");
        }
    }

    // Print Report
    public static void PrintReport() {
        System.out.println("\nPRODUCT REPORT");
        System.out.println("================================================================================");

        if (productList.isEmpty()) {
            System.out.println("No products available in the report.");
        } else {
            double totalValue = 0.0;
            for (int i = 0; i < productList.size(); i++) {
                ReportData p = productList.get(i);
                System.out.println("PRODUCT " + (i + 1));
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("PRODUCT CODE >>\t\t" + p.getProductCode());
                System.out.println("PRODUCT NAME >>\t\t" + p.getProductName());
                System.out.println("PRODUCT CATEGORY >>\t" + p.getProductCategory());
                System.out.println("PRODUCT WARRANTY >>\t" + p.getProductWarranty());
                System.out.println("PRODUCT PRICE >>\t" + p.getProductPrice());
                System.out.println("PRODUCT LEVEL >>\t" + p.getProductStock());
                System.out.println("PRODUCT SUPPLIER >>\t" + p.getProductSupplier());
                System.out.println("--------------------------------------------------------------------------------");

                totalValue += (p.getProductPrice() * p.getProductStock());
            }

            int totalCount = productList.size();
            double averageValue;
            if (totalCount > 0 ){
                averageValue = (totalValue / totalCount);
            }
            else{
                averageValue = 0.0;
            }

            System.out.println("================================================================================");
            System.out.println("TOTAL PRODUCT COUNT: " + totalCount);
            System.out.println("TOTAL PRODUCT VALUE: R " + totalValue);
            System.out.println("AVERAGE PRODUCT VALUE: R " + Math.round(averageValue));
            System.out.println("================================================================================");
        }
    }

    // Exit Application
    public static void ExitApplication() {
        System.out.println("Exiting application. Goodbye!");
    }
}

 class ReportData {
    private String productCode;
    private String productName;
    private String productCategory;
    private String productWarranty;
    private double productPrice;
    private int productStock;
    private String productSupplier;

    // Constructor
    public ReportData(String productCode, String productName, String productCategory, String productWarranty, double productPrice, int productStock, String productSupplier) {
        this.productCode = productCode;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productWarranty = productWarranty;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productSupplier = productSupplier;
    }

    // Getters
    public String getProductCode() {
        return productCode; 
    }
    
    public String getProductName() { 
        return productName; 
    }
    
    public String getProductCategory() {
        return productCategory; 
    }
    
    public String getProductWarranty() {
        return productWarranty; 
    }
    
    public double getProductPrice() {
        return productPrice; 
    }
    
    public int getProductStock() { 
        return productStock; 
    }
    
    public String getProductSupplier() {
        return productSupplier;
    }

    // Setters
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    public void setProductName(String productName) { 
        this.productName = productName;
    }
    
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory; 
    }
    
    public void setProductWarranty(String productWarranty) { 
        this.productWarranty = productWarranty;
    }
    
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }
    
    public void setProductSupplier(String productSupplier) { 
        this.productSupplier = productSupplier;
    }
}

