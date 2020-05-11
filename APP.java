import java.util.*; //package
abstract class title  //cannot be instantiated
{
    public void display() //method to display the title
    {
      System.out.println("\t\t\t\t*************************************************************");
      System.out.println("\t\t\t\t******************Library Management System******************");
      System.out.println("\t\t\t\t*************************************************************");
    }
}
class Book //class- book to have book details
{
    String title, author;
    int id, cost, student_id=0; //id - primary key of the book, unique for all
    boolean issued_flag = false; //checks whether the book is issued to the student.
    //contructor Book to initialise the book details
    //book has properties- title, author, id and cost
    Book(String title, String author, int id, int cost)
    {
        this.title = title;
        this.author = author;
        this.id = id;
        this.cost = cost;
    }
}
//student class to store the student details
class Student
{  //student has name, registration number,no. of issued books.
    String name;
    int regno, issued_books = 0;
    Student(String name, int regno){
        this.name= name;
        this.regno = regno;
    }
}
//class library to have details of the books and students altogether.
class Library
{  //scanner is used to take input
    Scanner inp = new Scanner(System.in);
    //Arraylist to store students and book object
    ArrayList<Student> students= null;
    ArrayList<Book> books= null;
    //passing arraylist in Library constructor.
    Library(ArrayList<Student> students, ArrayList<Book>books)
    {
        this.students = students;
        this.books = books;
    }
    //method when student comes to issue book from library
    void issue_book()
    {
       try{
        System.out.println("Enter registration number: ");
        //scanner used in taking reg num as input 
        int regno = inp.nextInt();
        //initially no book issued so flag false
        boolean student_flag=false, book_flag=false;
        System.out.println("Enter book id: ");
        //bid is book id
        int bid = inp.nextInt();

        for(int i=0;i<students.size();i++)
        {
            if(students.get(i).regno == regno)
            {
                student_flag = true;
                // if 3 books already issued then no more books can be issued
                if (students.get(i).issued_books == 3)
                {
                    System.out.println("Cannot issue more than three books at a time.");
                    break;
                } 
                else
                {
                for(int j=0;j<books.size();j++)
                {
                    if(books.get(j).id == bid)
                    {
                        book_flag = true;
                        if (books.get(j).issued_flag)
                        {  //for a particular book already issued 
                            System.out.println("Book Already Issued");
                            break;
                        }
                        else
                        {
                            System.out.println("Book Issued");
                            books.get(j).issued_flag = true;
                            students.get(i).issued_books++;
                            books.get(j).student_id = students.get(j).regno;
                            break;
                        }
                    }
                }
                if(!book_flag)
                {
                    System.out.println("Book not found");
                }
                break;
            }
            }
        }
        if (!student_flag)
        {
            System.out.println("Student Not Found!");
        }
     }
    catch(InputMismatchException exp)
	{
		System.out.println("you entered wrong");
	} 
    }
    void return_book()
    { //when a students return a book back to the library.
       try
       {
        System.out.println("Enter registration number: ");
        int regno = inp.nextInt();
        boolean student_flag=false, book_flag=false;
        System.out.println("Enter book id: ");
        int bid = inp.nextInt();

        for(int i=0;i<students.size();i++)
        {
            if(students.get(i).regno == regno)
            {
                student_flag = true;
                for(int j=0;j<books.size();j++)
                {
                    if(books.get(j).id == bid)
                    {
                        book_flag = true;
                        if (books.get(j).issued_flag)
                        {
                            System.out.println("Book Returned");
                            books.get(j).issued_flag = false;
                            students.get(i).issued_books--;
                            break;
                        }
                        else
                        {
                            System.out.println("Please Check the book ID.");
                            break;
                        }
                    }
                }
                if(!book_flag)
                {
                    System.out.println("Book not found");
                }
                break;
            }
        }
        if (!student_flag)
        {
            System.out.println("Student Not Found!");
        }
    }
      catch(InputMismatchException exp)
	{
		System.out.println("you enter error");
	} 
    }

    void search()
    { // searching the book if it is available in library or not
     try
     {
       System.out.println("Enter Book ID: ");
        int bid = inp.nextInt();
        boolean book_flag = false;
        for (int i=0;i<books.size();i++)
        {
            if(books.get(i).id == bid)
            {
                book_flag = true;
                if (books.get(i).issued_flag)
                {
                    for(int j=0;j<students.size();j++)
                    {
                        if(students.get(j).regno == books.get(i).student_id)
                        {
                            System.out.println("Book is not available...");
                            System.out.println("Book ID: " + books.get(i).id);
                            System.out.println("Book Name: " + books.get(i).title);
                            System.out.println("Book Author: " + books.get(i).author);
                            System.out.println("Cost of the book: " + books.get(i).cost);

                            System.out.println("Student details who have taken the book are:");

                            System.out.println("Name: " + students.get(j).name);
                            System.out.println("Reg. no.: " + students.get(j).regno);

                        }
                    }
                } 
                else
                 { // book details
                    System.out.println("Book is available...");
                    System.out.println("Book ID: " + books.get(i).id);
                    System.out.println("Book Name: " + books.get(i).title);
                    System.out.println("Book Author: " + books.get(i).author);
                    System.out.println("Cost of the book: " + books.get(i).cost);

                }
            }
        }
       }
    catch(InputMismatchException exp)
	{
		System.out.println("you entered wrong");
	} 
    }
    
}
//main class
public class APP extends title{
    public static void main(String...args)
     {
        Scanner input = new Scanner(System.in);
        title obj=new APP();
         obj.display();
        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<Student> students = new ArrayList<Student>();
        //books
        books.add(new Book("Weather", "Jenny Offill", 1, 1000));
        books.add(new Book("Astrophysics for People in a Hurry", "Niel De Grasse Tyson", 2, 200));
        books.add(new Book("Java", "XYZ", 3, 500));
        books.add(new Book("Networks", "ABC", 5, 800));
        books.add(new Book("Operating sysyem", "PQR", 7, 900));
        //students
        students.add(new Student("Krishanpal", 11805056));
        students.add(new Student("Shivam", 11856432));
        students.add(new Student("Shivank", 11856452));
        students.add(new Student("Saumy", 11856498));
        students.add(new Student("Ansh", 11856461));
        
        Library l = new Library(students, books);
        while(true)
        {
            System.out.println();
            System.out.println("1. View Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            switch(input.nextInt())
            {
                case 1:
                    l.search();
                    break;
                case 2:
                    l.issue_book();
                    break;
                case 3:
                    l.return_book();
                    break;
                case 4: 
                    System.exit(0);
                default:
                    System.out.println("Terminating program...");
                    System.exit(0);
            }
        }
    
    }
}