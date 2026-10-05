# Activité Pratique N°1 : Inversion de Contrôle (IoC) et Injection des Dépendances (DI)

Ce projet illustre la mise en œuvre des concepts fondamentaux d'architecture logicielle en Java : le **couplage faible**, le principe d'**inversion des dépendances (DIP)** et l'**injection des dépendances (DI)**.

---

## 🎯 Objectifs de l'activité

1. **Créer l'interface `idao`** comportant la méthode `double getData()`.
2. **Créer les implémentations de cette interface** :
   - `daoImpl` (simulation d'une source Base de données).
   - `daoImplV2` (simulation d'une source Web Service).
3. **Créer l'interface `imetier`** comportant la méthode `double calcul()`.
4. **Créer l'implémentation `metierImpl` en couplage faible** :
   - Dépendance vers l'interface `idao` (et non vers une classe concrète).
   - Prise en charge de l'injection par **constructeur** et par **setter**.
5. **Tester l'injection statique dans la classe `pres`**.

---

## 📂 Structure du Projet

```text
Activite-Pratique-1-Injection-Dependances/
├── pom.xml
└── src/
    └── main/
        └── java/
            ├── dao/
            │   ├── idao.java         # Interface du contrat d'accès aux données
            │   ├── daoImpl.java      # Implémentation V1 (Base de données)
            │   └── daoImplV2.java    # Implémentation V2 (Web service)
            ├── metier/
            │   ├── imetier.java      # Interface du contrat métier
            │   └── metierImpl.java   # Implémentation métier (couplage faible)
            └── pres/
                └── pres.java         # Classe de test / présentation (instanciation et injection)
```

---

## 🏗️ Diagramme de Classes & Architecture

Le schéma ci-dessous met en évidence le **couplage faible** : la classe `metierImpl` est liée exclusivement à l'interface `idao`. Elle ignore totalement quelle implémentation concrète (`daoImpl` ou `daoImplV2`) sera utilisée à l'exécution.

```mermaid
classDiagram
    direction LR

    class idao {
        <<interface>>
        +getData() double
    }

    class daoImpl {
        +getData() double
    }

    class daoImplV2 {
        +getData() double
    }

    class imetier {
        <<interface>>
        +calcul() double
    }

    class metierImpl {
        -dao: idao
        +metierImpl()
        +metierImpl(dao: idao)
        +setDao(dao: idao) void
        +calcul() double
    }

    class pres {
        +main(args: String[]) void
    }

    idao <|.. daoImpl : implements
    idao <|.. daoImplV2 : implements
    imetier <|.. metierImpl : implements
    metierImpl o--> idao : utilise (Couplage faible)
    pres ..> metierImpl : instancie & injecte
    pres ..> daoImpl : instancie
```

---

## 💻 Description et Code Source

### 1. Couche DAO (Accès aux Données)

#### Interface `idao.java`
Définit le contrat d'accès aux données :
```java
package dao;

public interface idao {
    double getData();
}
```

#### Implémentation V1 : `daoImpl.java` (Base de Données)
```java
package dao;

public class daoImpl implements idao {
    // Par exemple version DataBase
    @Override
    public double getData() {
        System.out.println("Version de base de donnees");
        return 10;
    }
}
```

#### Implémentation V2 : `daoImplV2.java` (Web Service)
Permet de simuler une évolution ou une autre source de données sans toucher au code de la couche métier :
```java
package dao;

public class daoImplV2 implements idao {
    // Par exemple version WebUI / Web Service
    @Override
    public double getData() {
        System.out.println("Version de web service");
        return 20;
    }
}
```

---

### 2. Couche Métier

#### Interface `imetier.java`
Définit le contrat des opérations métier :
```java
package metier;

public interface imetier {
    double calcul();
}
```

#### Implémentation `metierImpl.java` (Couplage Faible)
Cette classe dépend uniquement de l'interface `idao`. Elle propose deux mécanismes pour injecter cette dépendance :
- Un constructeur par défaut et un mutateur `setDao(...)` (**Injection par Setter**).
- Un constructeur avec paramètre `metierImpl(idao dao)` (**Injection par Constructeur**).

```java
package metier;
import dao.idao;

public class metierImpl implements imetier {
    // Couplage faible : référence vers l'interface
    private idao dao;

    // Constructeur par défaut
    public metierImpl(){
        this.dao = null;
    }

    // Constructeur avec paramètres
    public metierImpl(idao dao){
        this.dao = dao;
    }

    // Mutateur pour l'injection par Setter
    public void setDao(idao dao){
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double a = dao.getData();
        double resultat = a + 5;
        return resultat;
    }
}
```

---

### 3. Couche Présentation : `pres.java`

La classe `pres` initialise les composants et réalise l'**injection de dépendances statique** :

```java
package pres;
import dao.daoImpl;
import metier.metierImpl;

public class pres {
    public void main(String args[]){
        System.out.println(" ******** Activite-Pratique-1-Injection-Dependances ******** ");
        daoImpl a = new daoImpl();

        // Option 1 : Injection via constructeur avec paramètre
        // metierImpl b = new metierImpl(a);

        // Option 2 : Injection via Setter
        metierImpl b = new metierImpl();
        b.setDao(a);

        System.out.println(b.calcul());
    }
}
```

#### Résultat d'exécution en console :
```text
 ******** Activite-Pratique-1-Injection-Dependances ******** 
Version de base de donnees
15.0
```

> **Remarque :** Si l'on remplace `daoImpl a = new daoImpl();` par `daoImplV2 a = new daoImplV2();`, la méthode `calcul()` retourne `25.0` (`20 + 5`), tout en affichant `"Version de web service"`, démontrant ainsi la flexibilité du couplage faible.

---

## 🔄 Comparaison des Approches d'Injection

| Approche | Description | Avantages |
|---|---|---|
| **Injection par Constructeur** (`new metierImpl(dao)`) | L'objet est instancié avec toutes ses dépendances dès sa création. | L'objet est toujours dans un état cohérent et prêt à l'emploi. |
| **Injection par Setter** (`b.setDao(dao)`) | L'objet est instancié vide puis la dépendance est injectée via la méthode `setDao`. | Permet de changer dynamiquement de dépendance au cours du cycle de vie de l'objet. |
| **Injection Dynamique** (par Réflexion / Fichier de configuration) | Chargement des classes avec `Class.forName()` et instanciation dynamique. | L'application est totalement fermée à la modification et ouverte à l'extension (Principe OCP). |
| **Injection avec Framework (Spring)** | Gestion automatique du cycle de vie et des dépendances par le conteneur IoC (XML ou Annotations `@Autowired`). | Suppression complète du code "boilerplate" d'instanciation. |

---

## 🛠️ Compilation et Exécution

### Prérequis
- Java JDK 17+ (ou compatible)
- Apache Maven

### Commandes Maven
```bash
# Compilation du projet
mvn clean compile

# Exécution des tests ou packaging
mvn package
```
