# PROJET JEE - Enchères

## Équipe :
- Mourad Oujdari
- Antoine Gilles
- Yoann Pommier


___

## Intégrer le projet :

1. Télécharger et installer [Git pour windows](https://git-scm.com/downloads)
2. Ouvrir le powershell (Win + R => powershell)
3. éxécuter les commande : 
   > Se déplacer dans le dossier parent du dossier du projet
   ````bash
    cd D:
   ````

   > Configurer pour le proxy
   ````bash
    git config --global http.proxy http://proxy-sh.ad.campus-eni.fr:8080
   ````
 
   > Cloner le projet
   ````bash
   git clone https://github.com/NnaoyP/eni-auctions-project.git
   ````

___

## Utilisation de git et règles du gitflow

### Dictionnaire :

> **git:** Git est un outil de gestion de version

> **repository (ou repo):** Un dossier de projet géré avec git

> **github:** Un outil de gestion de repository

> **gitflow:** manière dont une équipe va utiliser les outils git

> **master:** Partie principale du projet, elle doit contenir le contenu de prod (pas de test dessus).

> **commit:** Ensemble de modifications ajoutés par la commande commit.

> **banche:** Partie séparée de la master ou d'une autre branche elle possède ses propres versions et commits.

> **merge:** Fusion de deux branches.

> **pull request:** Demande de merge d'une branche vers une autre avec Github.


### Commandes et Gitflow

Une fois les modifications faites il faut ajouter les fichiers, il y a quatres méthodes :
````bash
git add . # Ajoute toutes les modifications.

git add folder/file1 folder2/file3 # Ajoute des fichiers en particulier, préciser seulement le dossier ajoute tous les fichiers du dossier.

git commit -a # Crée un commit sur tous les fichier modifiés (attention ne prend pas en compte les nouveaux fichiers).

git commit file folder/file2 # Même chose que pour add.
````

Une fois les modifications ajoutés, il faut créer un commit, un billet de modification. Mieux vaut sectionner vos modifications et les mettre dans plusieurs commit si elles ne sont pas liés :
````bash
git commit # Cette commande va vous ouvrir l'éditeur de texte par défault donné à l'instalation de git.

git commit -m "Message de commit" # Permet d'écrire le message de commit dans la commande.

# Vous pouver mettre plusieurs instruction au commit comme :
git commit-am "Mon message" # Le a correspond au commit -a de la commande précédente.
````

Il faut aussi savoir naviguer entre les branches, faites attention à bien commit votre travail avant de changer de branche
````bash
git checkout nom/de-la-branche
````

Pour créer une branche, rien de plus simple :
(tips : Créer une nouvelle branche quand vous avez des modification en cours fera venir ces modification sur la nouvelle branche)
````bash
git checkout -b nom/de-la-nouvelle-branche # Attention la version de cette nouvelle branche se basera sur la branche sur laquelle vous êtes. Vous devez toujours vous trouver sur la branche master pour créer une branche
````
Pour envoyer un commit sur github il suffit de faire :
````bash
git push

# S'il sagit du premier commit d'une nouvelle branche il va vous demander de créer l'upstream, mais git est sympa et donne la commande adapté, elle ressemble généralement à :
git push --set-upstream origin dev # Il suffiras de la copier coller
````

Pour récupérer le travail fait par les autres il faut pull:
````bash
git pull

# Si voys n'êtes pas seul sur la branche pensez à faire ce pull avant de push :
git pull --rebase
````


### Règles du gitflow :

- Les branches devront toujours se baser sur master, vérifiez bien avant de créer une nouvelle branche.
- Toujours créer des pull request sur vos branches.
- Une branche devra respecter ces règles de lisibilité :
  
  > Elle devra avoir un de ces trois préfix
  > - Mep/
  > - Fix/
  > - Feature/
  >
  > Elle devra ensuite comporter vos initiales
  > 
  > exemple de nom de branche : **Mep/YP-page-accueil**

- Un commit devra toujours avoir un ou plusieus de ces préfix :
  - **[fix]** Si le commit corrige un bug.
  - **[up]** Si le commit est une amélioration.
  - **[style]** Si le commit est une amélioration de la syntaxe.
  
- Si vous avez des questions, demandez moi 😁