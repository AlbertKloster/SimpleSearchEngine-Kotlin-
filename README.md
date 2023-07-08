# Stage 2/6: Expand the search
## Description
Now, let's make our search a little more complex. Let's write a program that performs multiple searches in multiple text lines.

## Objectives
Write a program that reads text lines from the standard input and processes single-word queries. The program must output all lines that contain the string from the query. For this stage, this should include the case where the query string appears as a substring of one of the text lines. For example, the query "bc" should be found in a line containing "abcd".

You may choose what the text represents in your project. For example, each line may describe:

- a person represented by the first name, the last name, and optionally an email;

- an address of a building represented by the country, city, state, street, and zip code;

- a book represented by its ISBN, title, author/authors, publisher, and so on.

You can use any of these options or come up with your own, because your search algorithm should work regardless of what the text actually represents.

Here is an example of an input line. It contains three items: the first name, the last name, and an email address.
```
Elsa Sanders elsa@gmail.com
```

In this example, all items are separated by spaces.

The search should be case insensitive and ignore all extra spaces.

First, the user should input a number N, which is the number of data lines they are going to enter next. Then the user enters N lines with data. After that, the user enters a number M, which is the number of search queries. After each query, the program should print the information it managed to find among the data. You can see this searching process in the example below.

## Example
In the following example, we use several names and e-mails as a dataset. The lines that start with `>` represent the user input. Note that these symbols are not part of the input.
```
Enter the number of people:
> 6
Enter all people:
> Dwight Joseph djo@gmail.com
> Rene Webb webb@gmail.com
> Katie Jacobs
> Erick Harrington harrington@gmail.com
> Myrtle Medina
> Erick Burgess

Enter the number of search queries:
> 3

Enter data to search people:
> ERICK

People found:
Erick Harrington harrington@gmail.com
Erick Burgess

Enter data to search people:
> unknown
No matching people found.

Enter data to search people:
> WEBB@gmail.com

People found:
Rene Webb webb@gmail.com
```
