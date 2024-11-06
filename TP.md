# TP 1 - JSX

## 1.1 - modification du JSX existant

### Contexte

Vous venez de créer votre application React avec React-create-app, bravo !

Gardez votre serveur node démarré vous pourrez faire les modifications à chaud.

Pour démarrer votre serveur node et application React :

```
npm install
```

côté code l'application démarrage avec le index.js puis le App.js

### Ajout d'un paragraphe

- 1.1.1 : En dessous du paragraphe "Edit src/App.js save to reload", ajouter un paragraphe (balise html p) avec le
  message suivant :
  "This is test application of <YourName>, to launch app execute this command "

- 1.1.2 : Ajouter ensuite le texte npm start avec le style du texte "src/App.js" (balise html code)

- 1.1.3 : Maintenant j'aimerais avoir <YourName> avec un style particulier, pour cela dans le fichier index.css
  rajouter le code css suivant :

```
span.firstname {
  color: crimson;
}
```

pour vérifier votre résultat comparé à l'image data/react-app-1.1.3.png

## 1.2 - Créer un composant avec le code JSX ajouté

### 1.2.1 Création d'un composant basique introduce

Tout est dans le texte, bien sur on fait du function component

Vous allez créer un composant qui va s'appeler "introduce", vous allez dans un premier temps juste déplacer
le paragaphe "this is test application ..." et pas encore le texte "npm install"

### 1.2.2 introduisons un paramètre pour variabiliser le nom

Je souhaiterais ajouter 2 composants dans l'application principale (App.js), un premier avec votre prénom,
et un 2e avec le prénom de votre choix.

Bien sur, on réutilise le même composant normalement le code dans App.js doit ressembler à cela :

```
<introduce firstName="Nicolas"></introduce>
<introduce firstName="Didier"></introduce>
```

### 1.2.3 ajouter le texte "npm install" dans ce composant

Vous avez des problèmes c'est normal, quand on créé un composant, on renvoit une seule balise JSX
maintenant le fwk react vous donne la réponse dans l'erreur retenez bien cette notion

### 1.2.4 Rendre ce componsant dans une boucle

maintenant je voudrais que l'on rende ce composant avec tous ces prénoms

Rajouter cette liste avec la fonction App

``` 
const firstNames = ["Pierre", "Paul", "Jacques", "Didier", "Nicolas"]
```

Vérifier le console.log

### 1.2.5 Rendre un composant avec un type objet en entrée

Ajouter ces données comme vous avez fait pour "firstNames"
``` 
const peoples = [
  {
    firstName : "Pierre",
    lastName : "Richard",
    photo : "", 
  },
    {
    firstName : "Paul",
    lastName : "Verlaine",
    photo : "", 
  },
    {
    firstName : "Jacques",
    lastName : "Brel",
    photo : "", 
  },
    {
    firstName : "Didier",
    lastName : "Drogba",
    photo : "", 
  },
    {
    firstName : "Nicolas",
    lastName : "Le Jardinier",
    photo : "", 
  },
]
``` 

On va créer un nouveau composant (comme vous avez fait pour Introduce).
Ce composant va s'appeler "Profile". Il va prendre des props contenant un objet personne déclarer comme suit :

```
export type Person = {
    firstName: string;
    lastName: string;
    photo?: string;
}
```

De plus on voit que l'attribut "photo" est optionnel donc il faudra afficher une image par défaut dans ce cas.

**BONUS** : essayes de le faire en allant le plus loin possible avec TypeScript.

voici le css à rajouter pour cela :
```
@import url("https://fonts.googleapis.com/css2?family=Baloo+Paaji+2:wght@400;500&display=swap");

.container {
  margin-top:50px;
  display: grid;
  grid-template-columns: 300px 300px 300px;
  grid-gap: 50px;
  justify-content: center;
  align-items: center;
  height: 70vh;
  background-color: #f5f5f5;
  font-family: 'Baloo Paaji 2', cursive;
}

.card {
  background-color: #222831;
  height: 20rem;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  align-items: center;
  box-shadow: rgba(0, 0, 0, 0.7);
  color: white;
}

.card__lastname {
  font-size: 1.8em;
  font-weight: 900;
}

.card__firstname {
  font-size: 1.5em;
}

.card__image {
  height: 160px;
  width: 160px;
  border-radius: 50%;
  border: 5px solid #272133;
  margin-top: 20px;
  box-shadow: 0 10px 50px rgba(235, 25, 110, 1);
}

```

exemple de html associé


```
    <div class="container">
        <div classN="card">
            <img src="http://monurl" alt="Person" class="card__image"/>
            <p>
                <span class="card__lastname">NOM</span>,
                <span class="card__firstname"> prénom</span>
            </p>
        </div>
    </div>
```

l'image en base64 par défaut (la citrouille) se trouve dans data/citrouille-content-base64.txt

pour vérifier votre résultat comparé à l'image data/react-app-1.2.5.png


# TP 2 - Router

Le router va vous permettre d'avoir une navigation de "page" en "page"

Il faut un module supplémentaire, installation de react router :

```
npm i react-router-dom
```

refaire un npm install puis start

Vérifier votre package.json il y a des librairies qui ont été rajoutés

## 2.1 - Installation du routeur

L'objectif est d'avoir 2 pages d'aller d'une page à une autre

### 2.1.1 - Integration d'un BrowserRouter

Voici la documentation officielle https://reactrouter.com/en/main/router-components/browser-router

Il n'y a rien à observer ici sauf que l'application n'est pas cassé :)

### 2.1.2 - intégration en bas de votre page avec du routing

```
      <div>
          <div>
            <ul>
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <Link to="/about">About</Link>
              </li>
            </ul>

            <hr />
            <Routes>
              <Route exact path="/" element={<Home />} />
              <Route path="/about" element={<About />} />
            </Routes>
          </div>
      </div>
```
les 2 composants Home et About peuvent être ajouter dans un répertoire page
Discutons-en

### 2.1.3 - arrêtons de jouer commencer une "vraie" app

Vous n'avez besoin de rie n'effacer on va faire une nouvelle app dans l'app avec un composants page/app2.
On va faire le remplacement dans index.js

Faire une navbar qui va changer le contenu de la navigation
