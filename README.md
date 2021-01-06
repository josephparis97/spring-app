# spring-app

Projet d'évaluation de compétences sur Spring Boot et les tests unitaires.

# Description du projet

	Implémenter le scénario Cucumber ci-dessus en utilisant Java, Spring Boot, Cucumber, Maven, TestNG et une base de donnée H2.
	
	Le scénario est le suivant :
		Fonctionnalité: Modifier l'adresse d'un abonné
			Scénario: Modification de l'adresse d'un abonné
				**Etant** donné un abonné avec une adresse principale en France
				**Lorsque** le conseiller modifie l'adresse de l'abonné
				**Alors** la nouvelle adresse de l’abonné est enregistrée sur l'ensemble des contrats de l'abonné
				**Et** un mouvement de modification d'adresse est créé avec la nouvelle adresse
				
	:bulb: Pour cela, il faudra implémenter les APIs pour :
	- Créer un nouvel abonné
	- Souscrire à un contrat
	- Modifier l’adresse de l’abonné
	- Récupérer les informations d’un abonné
	- Récupérer l’historique des mouvements de modification effectués pour un abonné
	
	:bulb: En sachant que :
	- Un abonné peut avoir plusieurs contrats, a un identifiant, un nom, un prénom et une adresse principale
	- Un contrat a un identifiant et une adresse
	- L’API sur les mouvements de modification doit permettre de récupérer les
	informations suivantes : 
		- identifiant de mouvement 
		- identifiant d’abonné 
		- identifiant de contrat 
		- type de modification 
		- date de modification 
		- nouvelle valeur de modification
		- ancienne valeur de modification
		- La modification d’adresse est un type de mouvement de modification

## L'API et ses enpoints :

1. Les abonnées
:point_right: http://localhost:8080/abonnes
	- Get retourne la liste des abonnés
	- Put + Json /{id} pour modifier un abonné
	- Post + Json pour ajouter un nouvel abonné
	- Delete /{id} pour supprimer un abonné

2. Les contrats
:point_right: http://localhost:8080/contrats
	- Get retourne la liste des contrats
	- Put + Json /{id} pour modifier un contrat
	- Post + Json pour ajouter un nouvel contrat
	- Delete /{id} pour supprimer un contrat

3. Les mouvements 
:point_right: http://localhost:8080/mouvements
	- Get retourne la liste des mouvements
	- Get /abonnes/{id} pour lister les mouvements d'un abonné
	- Post + Json pour ajouter un nouvel contrat
	- Delete /{id} pour supprimer un contrat


## Lancer l'Api Rest

Pour lancer l'Api REST, il faut exécuter /spring-app/src/main/java/org/example/server/SpringAppApplication.java

## Lancer les tests Unitaires avec l'API

Exécuter le fichier /spring-app/src/test/java/ModificationAdresseTest.java avec TestNG

## Lancer les test unitaire sans API (avec Mock)

Exécuter le fichier /spring-app/src/test/java/mocked/ModificationAdresseMockTest.java avec TestNG
