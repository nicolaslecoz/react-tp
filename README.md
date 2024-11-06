# Développpement React classique (avec un environnement Node)

## Pré-requis

- Avoir Node d'installer
- Pré-requis++ avoir nvm d'installer : https://github.com/nvm-sh/nvm?tab=readme-ov-file#installing-and-updating

## Fixer la bonne version de node

créer un fichier .nvmrc avec la version v18.20.1

```
echo 'v18.20.1' > .nvmrc
```
puis

```
nvm install
```
## Créer une application react avec 

Application se prénommant my-app

```
npx create-react-app my-app
cd my-app
npm install
npm start 
```
# Développement React sans environnement Node (avec Docker)

## Pré-requis

Avoir d'installer docker et docker-compose

## Construire le conteneur

```
docker-compose build
```

## Démarrer le conteneur

```
docker-compose up
```
