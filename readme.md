# Review Site Full Stack

**Module 7 Graded Project**

## Objective

Building off of the Module 5 Graded Project (Reviews Site), either extend off the project or start over to create a fully functional reviews site that utilizes JPA as well as a Populator to begin the functionality of the website

### Tasks

Feel free to use appropriate class names other than `Review` and `Category`, but these instructions will refer to `Review` and `Category`.

*	Add the following dependencies to `build.gradle` (or use Spring Initializr to create a new `build.gradle`)
	*	JPA (spring-boot-starter-data-jpa)
	*	H2
*	Create a `Category` class that:
	*	is a JPA entity.
	*	contains an instance variable referencing the `Review`s it contains.
	*	configures an appropriate JPA relationship to its reviews.
*	Update the `Review` class such that:
	*	it is a JPA entity.
	*	configures a JPA relationship to its associated category.
	*	allows for a description/content/body longer than 255 characters.
*	Update your view (templates/html/css) such that:
	*	there is a page that lists review categories, each of which links to the (details) page for a specific category.
	*	there is a page that lists the reviews for a chosen category, each of which links to the (details) page for a specific review.
	*	each review detail page has a link to the page for its category.

### Stretch Tasks

#### Tags

*	Create a `Tag` entity.
*	Update `Review` so that it can have tags associated with it. A review can have many tags, a tag can be assigned to many reviews.
*	Display tags on the review details page.
*	Create a page that displays links to all of the reviews associated with a given tag.

#### Stretchier

*	Style your tags list template as a tag cloud, making tags which appear more often larger and/or bolder and those that appear less often smaller and/or lower weight.
*	Allow creation and deletion of tags from a review using `<form>` and `<button>` elements along with the appropriate controller method(s).

