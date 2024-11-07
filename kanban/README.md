# Sujet de TP : Création d'un tableau Kanban type Trello avec React

## Objectif du projet

Le but de ce TP est de créer une application front-end avec **React** qui reproduit un tableau Kanban type **Trello**.

Vous utiliserez un backend déjà fourni pour gérer les données (boards, colonnes, cartes, commentaires). Une documentation est disponible ici : [https://kanban-api.joza-it.fr/swagger-ui.html](https://kanban-api.joza-it.fr/swagger-ui.html).

### ***Vous n'avez pas besoin de cloner ce projet, sauf indication contraire du professeur.***

L'application permettra aux utilisateurs de créer et de gérer des boards, des colonnes et des cartes avec une interface réactive et dynamique.

### Fonctionnalités à implémenter

Votre application doit couvrir les fonctionnalités suivantes :

1. **Page de login** :
    - Si l'utilisateur n'est pas authentifié, il sera redirigé vers une page de login.
    - Un formulaire de connexion est présenté avec un lien pour créer un nouveau compte.
    - Si l'utilisateur n'a pas de compte, il peut cliquer sur un lien "Créer un compte" pour accéder à la page d'inscription.
    - Si l'utilisateur est déjà connecté (authentifié), il sera automatiquement redirigé vers la liste des boards.

2. **Page d'inscription** :
    - Un formulaire permet à l'utilisateur de créer un compte avec un email et un mot de passe.
    - Après la création du compte, il est redirigé vers la page de connexion ou connecté automatiquement.

3. **Page des boards** :
    - Une fois connecté, l'utilisateur est redirigé vers une page listant tous ses boards.
    - L'utilisateur peut :
        - Créer un nouveau board (avec un formulaire simple pour entrer le nom du board).
        - Accéder à un board en cliquant sur celui-ci.

4. **Page du board** :
    - Sur cette page, l'utilisateur voit les colonnes créées pour ce board et les cartes assignées à chaque colonne.
    - L'utilisateur peut :
        - **Créer** de nouvelles colonnes.
        - **Modifier** le nom des colonnes.
        - **Créer** de nouvelles cartes dans une colonne.
        - **Modifier** les cartes (nom, contenu).
        - **Déplacer** une carte d'une colonne à une autre.
        - **Supprimer** des colonnes et des cartes.
    - L'utilisateur peut également modifier le **nom du board** ou le **supprimer**.

### Contraintes techniques

1. **React** :
    - Utiliser **React** pour créer l'application. Organisez votre code en composants réutilisables et respectez les bonnes pratiques de séparation des responsabilités.
    - Utilisation de **Typescript** (aucun fichier .js ou .jsx dans votre projet)
    - Utilisation de la version LTS de node.
    - Utilisation de NVM pour installer Node et un `.nvmrc` à la racine de votre projet.
    - Tous les fichiers de configuration (par exemple `package.json` ou `package-lock.json`) doivent être à la racine du projet
    - Gestion des états avec **useState** et **useEffect** pour les actions asynchrones.
    - Utilisation de la bibliothèque [Zustand](https://github.com/pmndrs/zustand) pour la gestion des états, notamment pour stocker les informations de l'utilisateur authentifié et toutes les autres donnés que vous jugez importantes.

2. **API et gestion des données** :
    - Le backend est fourni (API REST) [dont vous trouverez la documentation ici](https://kanban-api.joza-it.fr/swagger-ui.html), et vous devrez interagir avec lui pour récupérer et mettre à jour les données (boards, colonnes, cartes, etc.).
    - Utiliser la méthode **fetch** ou une bibliothèque comme **Axios** pour interagir avec l'API.
    - Gérez les réponses et les erreurs du serveur de manière appropriée (loading states, messages d’erreur).

3. **Navigation** :
    - Utiliser **React Router** pour la gestion des routes (ex. `/login`, `/signup`, `/boards`, `/boards/:id`).
    - PAS DE `window.location.replace(...)`, `window.location.href(...)`, etc

4. **Authentification** :
   - Le mot de passe doit contenir:
     - Au minimum 8 caractères
     - Au maximum 24 caractères
     - Au moins une lettre majuscule
     - Au moins une lettre minuscule
     - Au moins un chiffre
     - Au moins un caracère spécial
   - Lors de la connexion, l'API retourne un **token d'authentification** (JSON Web Token - JWT) que vous devrez stocker de manière sécurisée (dans le `localStorage` ou le `sessionStorage`).
     - Pour toutes les requêtes API autre que `/login` et `/register`, vous devez fournir ce token dans les headers de votre requêtes.

Exemple: 
```
const yourJWT = 'votreJwtIci'
const response = await axios.get("https://kanban-api.joza-it.fr/boards/", {
  headers: {
    Authorization: `Bearer ${yourJWT}`
  }
});
```
   - Vérifiez que l'utilisateur est authentifié avant d'accéder à certaines pages (liste des boards, board spécifique).

5. **Drag-and-drop** (Bonus) :
    - Implémenter un système de **drag-and-drop** pour déplacer les cartes entre les colonnes (vous pouvez utiliser la bibliothèque [react-beautiful-dnd](https://github.com/atlassian/react-beautiful-dnd) ou une autre de votre choix).

### Étapes du projet

1. **Installation et configuration** :
   - Installer node version LTS à l'aide d'un `.nvmrc` à la racine du projet 
   - Créez une nouvelle application React avec **Create React App**.
   - Installez les dépendances nécessaires : React Router, Axios (ou fetch), Zustand, et éventuellement une bibliothèque pour le drag-and-drop.

2. **Mise en place de l'authentification** :
    - Créez les pages de login et de création de compte avec les formulaires appropriés.
    - Gérer l'authentification avec les tokens JWT.

3. **Gestion des boards** :
    - Implémentez la page qui affiche la liste des boards.
    - Créez le formulaire de création de board.

4. **Gestion des colonnes et des cartes** :
    - Implémentez la page du board qui affiche les colonnes et les cartes.
    - Ajoutez les fonctionnalités de création, modification et suppression pour les colonnes et les cartes.
    - Implémentez le déplacement des cartes entre les colonnes.

### Bonus

- **Drag-and-drop** : Si vous avez terminé le projet de base, ajoutez la fonctionnalité de drag-and-drop pour déplacer les cartes entre les colonnes.
- **Commentaires** : Ajoutez une fonctionnalité qui permet de laisser des commentaires sur les cartes.

### Points de contrôle

Vous devrez rendre votre projet via un dépôt GitHub. Voici les points importants qui seront évalués :

- **Qualité du code** : Structure des composants, réutilisation, lisibilité du code.
- **Respect des fonctionnalités** : Toutes les fonctionnalités demandées sont implémentées correctement.
- **Gestion de l'état et des API** : Bonne gestion des états dans l'application (local state, useEffect), interactions avec l'API bien gérées (récupération, modification, suppression des données).
- **Navigation** : Navigation fluide entre les différentes pages (login, liste des boards, page d’un board).
- **Expérience utilisateur** : Application fonctionnelle, sans bugs bloquants et agréable à utiliser (feedback visuel, gestion des erreurs, etc.).

### Livrables attendus

- **Dépôt GitHub** avec le code source de l’application.
- **Documentation** : Un fichier `README.md` expliquant votre démarche et l'architecture du projet.

## ATTENTION
Le correcteur executera la commande `docker-compose up --build` pour executer votre projet. Ils doit donc il y a voir un `docker-compose.yml`, un `Dockerfile` et un `nginx.conf` à la racine de votre projet. Vous trouverez ces fichiers ci-dessous.

Il sera donc judicieux d'executer le docker-compose vous même au fur et à mesure de votre phase de développement pour éviter les mauvaise surprises.
Après avoir executé le `docker-compose.yml`, ouvrez la page [http://localhost:8081/](http://localhost:8081/). Si votre application apparaît à l'écran, c'est que votre correcteur pourra vous corriger.

---

### Ressources utiles
- [Fichier typescript avec tous les objets métiers utiles pour ce projet](https://kanban-api.joza-it.fr/domain.d.ts)
- [Documentation de l'API Kanban](https://kanban-api.joza-it.fr/swagger-ui.html)
- [Documentation officielle de React](https://reactjs.org/docs/getting-started.html)
- [Documentation de React Router](https://reactrouter.com/)
- [Axios pour les requêtes HTTP](https://github.com/axios/axios)
- [Zustand pour la gestion des états](https://github.com/pmndrs/zustand)
- [react-beautiful-dnd pour le drag-and-drop](https://github.com/atlassian/react-beautiful-dnd)

### docker-compose.yml

```yaml
services:
  react:
    container_name: kanban-front
    build: .
    ports:
      - 8081:80

    networks:
      - kanban-network
networks:
  kanban-network:

```

### Dockerfile
```dockerfile
FROM node:lts-alpine as builder
WORKDIR /app
COPY . .
RUN npm ci
RUN npm run build

FROM nginx:1.21.5-alpine
COPY --chown=nginx:nginx nginx.conf /etc/nginx/conf.d/default.conf
COPY --chown=nginx:nginx --from=builder /app/build /var/www/html/

```


### nginx.conf
```
server {
 listen 80;
 listen [::]:80;
 root /var/www/html/;
 index index.html;
 location / {
  try_files $uri $uri/ /index.html;
 }
}
```
