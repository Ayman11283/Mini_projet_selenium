# Mini Projet Selenium - Gestion de Produits

## Description
Ce projet contient des tests automatisés Selenium Java pour l'application Advantage Online Shopping.
Il s'agit d'une conversion du projet Robot Framework original.

## Tests Inclus
1. **AjoutUnitaireTest** - Test d'ajout d'un seul produit au panier
2. **MultipleProductsTest** - Test d'ajout de plusieurs produits (laptop, tablet, speaker)
3. **SuppressionProduitTest** - Test de suppression d'un produit du panier

## Structure du Projet
```
Mini_Projet_Selenium/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/
│   │       │   ├── ProductPage.java
│   │       │   ├── CartPage.java
│   │       │   └── DescriptionProductPage.java
│   │       └── utils/
│   │           └── CommonKeywords.java
│   └── test/
│       └── java/
│           ├── tests/
│           │   ├── AjoutUnitaireTest.java
│           │   ├── MultipleProductsTest.java
│           │   └── SuppressionProduitTest.java
│           └── utils/
│               └── BaseTest.java
├── pom.xml
├── testng.xml
└── README.md
```

## Technologies Utilisées
- Java 11
- Selenium WebDriver 4.15.0
- TestNG 7.8.0
- WebDriverManager 5.6.2
- Maven

## Prérequis
- JDK 11 ou supérieur
- Maven 3.6+
- Chrome Browser installé

## Installation et Exécution

### 1. Installer les dépendances
```bash
cd C:\Users\arabouh\Mini_Projet_Selenium
mvn clean install
```

### 2. Exécuter tous les tests
```bash
mvn test
```

### 3. Exécuter un test spécifique
```bash
mvn test -Dtest=AjoutUnitaireTest
mvn test -Dtest=MultipleProductsTest
mvn test -Dtest=SuppressionProduitTest
```

### 4. Exécuter avec TestNG XML
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## Page Objects
- **ProductPage** - Gestion de la page des produits et catégories
- **DescriptionProductPage** - Gestion de la page de description et ajout au panier
- **CartPage** - Gestion du panier et des opérations de suppression

## Utilitaires
- **BaseTest** - Configuration commune pour tous les tests (setUp/tearDown)
- **CommonKeywords** - Méthodes réutilisables pour les opérations communes

## URL de Test
https://advantageonlineshopping.com/#/

## Notes
- Les tests utilisent ChromeDriver qui est automatiquement géré par WebDriverManager
- Les rapports TestNG sont générés dans `target/surefire-reports/`
- Le navigateur s'ouvre en mode maximisé pour chaque test
- Les sleep() sont utilisés pour attendre le chargement des éléments (à optimiser avec WebDriverWait si nécessaire)
