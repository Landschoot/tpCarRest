# Serveur ftp
####Université Lille 1 - Master Miage FA - CAR
####Ludovic Landschoot

##Présentation
Le but du projet est la réalisation d'un serveur ftp.

####Comment le démarrer ?

* Run la classe "Application.java", elle se trouve à la racine.

#### Comment elle fonctionne ?

Information : Au lancement du serveur, une fonction d'initialisation se trouvant dans la classe "Application"
 permet d'initialiser le répertoire principal et le répertoire propre à chaque utilisateur dans la base.

* Se connecter avec un client ftp ou en ligne de commande.
* Les ports utilisés et le répertoire principal sont configurable dans la classe "ServerConfig.java".
* Les différents clients sont cités dans la classe "UserDB.java".
    * Le client identifié a accès et voit seulement son répertoire. Il ne peut donc pas voir les répertoires de niveau inférieur au sien, ni celui des autres utilisateurs.
* Possibilité de se connecter en anonyme.
* Les commandes supportées par le serveur sont citées plus bas.

##Architecture du projet
```
| src 
    | main                      
        | java                  
            | clientSession     : Ce qui concerne les sessions des clients
            | command           : Ce qui représente les commandes reçues
            | db                : Ce qui concerne les mocks de base de données et les services qui permet d'accéder aux données
            | request           : Ce qui permet de gérer les requêtes reçues
            | server            : Ce qui concerne le serveur et la configuration
        | ressources            
```

##Les différentes commandes traitées
| Commande      |   Information  |
| ------------- |: ------------- |
| USER | Permet d'authentifier le login |
| PASS | Permet d'authentifier le mot de passe |
| EPSV | Permet de se connecter en mode passif |
| PASV | Permet de se connecter en mode passif |
| CWD | Permet de changer de repertoire |
| LIST | Permet de lister les fichiers du dossier |
| MKD | Permet de créer un nouveau dossier |
| RMD | Permet de supprimer un dossier |
| DELE | Permet de supprimer un fichier |
| PWD | Permet de retourner le repertoire |
| RETR | Permet d'envoyer un fichier |
| STOR | Permet de recevoir un fichier |
| TYPE | Permet d'indiquer le type du fichier |
| SYST | Permet d'indiquer le système |
| QUIT | Permet de déconnecter l'utilisateur |