# **E-Shop**
**Muhammad Hilal Darul Fauzan**<br/>
**2206830542**<br/>
**Pemrograman Lanjut C**<br/>

### Deployment
Link untuk menuju aplikasi dapat diakses melalui [E-Shop](https://advpro-tutorial-hilaldfzn.koyeb.app).

### SonarCloud Report
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=hilaldfzn_tutorial-1&metric=coverage)](https://sonarcloud.io/summary/new_code?id=hilaldfzn_tutorial-1)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=hilaldfzn_tutorial-1&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=hilaldfzn_tutorial-1)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=hilaldfzn_tutorial-1&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=hilaldfzn_tutorial-1)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=hilaldfzn_tutorial-1&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=hilaldfzn_tutorial-1)

## **Tutorial Modul 1: Coding Standards**

**Reflection 1**

You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code. If you find any mistake in your source code, please explain how to improve your code.

Saya telah menerapkan beberapa *coding standard* pada Tutorial Modul 1, seperti:

* *Clean Code* <br/>
    Prinsip-prinsip *clean code* yang sudah saya terapkan, yaitu:
    1. Meaningful Names <br/>
        Penamaan untuk *variable* dan *method* yang ringkas, jelas, dan *to the point* sehingga kode saya dapat lebih mudah untuk dibaca dan dipahami serta menjelaskan arti dan maksud kegunaannya untuk apa.
    2. Function or Method <br/>
        Menurut saya, method-method yang saya kembangkan cukup singkat dan hanya fokus pada satu tugas. Penamaan method yang saya pilih juga deskriptif dan tidak menimbulkan *side effect* pada program secara umum. Method-method tersebut yang mencakup `CREATE`, `EDIT`, dan `DELETE`, semuanya terdapat dalam *class* yang sama. Ini seharusnya membuat kode lebih mudah untuk di-*debug* apabila terjadi kesalahan.
    3. Objects and Data Structures <br/>
        Saya telah menerapkan prinsip ini dalam kode yang saya buat. Saya mengimplementasikan abstraksi berupa *interface* dan mendeklarasikan atribut dengan akses *private* di dalam *class*, sehingga tidak dapat diakses langsung. Untuk mengakses atau memodifikasi atribut tersebut, saya menggunakan *setter* dan *getter*.

    Prinsip-prinsip *clean code* yang belum saya terapkan, yaitu:
    1. Comments        
        Sampai saat ini, saya belum menambahkan *comments* apa pun dalam kode saya karena saya merasa bahwa kode tersebut sudah cukup *concise*. Menurut saya, kode yang saya buat masih terbilang singkat serta mudah untuk dipahami dan dilakukan *tracing*, dan menggunakan penamaan yang jelas sehingga belum ada kebutuhan untuk menambahkan *comments*. Namun, saya terbuka untuk menambahkan *comments* jika kode akan menjadi lebih kompleks pada tutorial ke depannya.
        
    2. Error Handling <br/>
        Saya belum melakukan *error handling* karena menurut saya ini belum diperlukan. Hal ini dikarenakan kode saya pada bagian `CREATE`, `EDIT`, dan `DELETE` seharusnya tidak memunculkan error. Meskipun di method `findById()` kemungkinan terjadi error karena mengembalikan *null*. Namun, saya beranggapan bahwa ini tidak akan terjadi karena saya selalu mengirimkan ID yang valid. Jika ada kemungkinan bahwa method tersebut mengembalikan *null*, saya mungkin akan mencoba menggunakan `throw`, `try`, dan `catch`.

* *Secure Coding* <br/>
*Secure coding* yang telah saya terapkan adalah penggunaan method `POST` untuk `CREATE`, `EDIT` dan `DELETE` product. Namun, saya belum menerapkan *secure coding* dalam hal *Input Data Validation*. Hal ini terlihat pada kode saya yang belum menangani input *quantity* dengan nilai negatif atau non-integer. *Authentication* dan *Authorization* juga belum diterapkan pada tutorial ini.

Selama mengerjakan tutorial ini, saya mengalami beberapa kesalahan, seperti typo pada saat menambahkan dependencies untuk *unit* dan *functional test* serta lupa menambahkan '$' pada `@Value` yang mengganggu fungsi Selenium. Selain itu, saya juga menghadapai masalah pada *functional test*, di mana pada tutorial belum ada arahan untuk membuat *mapping* ke url `http://localhost:8080` yang menyebabkan *title* "ADV Shop" dan *element h3* dengan message "Welcome" tidak ditemukan pada page sehingga test menjadi gagal. Solusinya adalah dengan membuat *additional file* `HomeController.java` dengan `@RequestMapping("/")` pada *class* `HomeController` serta `@GetMapping("")` pada method `homePage()` yang me*return* "homePage" dan `HomePage.html` agar bisa mengakses `http://localhost:8080` tanpa muncul error sehingga *functional test* akan berhasil.

> [!NOTE]
> Untuk *Git Flow* saya menerapkan *branching strategy* seperti yang dijelaskan pada modul 1. <br/>
> Untuk *Testing* akan diterapkan pada bagian kedua dari tutorial ini.

**Reflection 2**

1. After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?

    Setelah menerapkan *unit test*, saya merasa kode yang saya buat menjadi lebih andal. Tidak ada standar pasti mengenai jumlah *unit test* yang diperlukan untuk suatu *class*, tetapi beberapa ahli merekomendasikan target *code coverage* sekitar 80%. Namun, penting untuk menguji keseluruhan fitur untuk memastikan tidak ada kasus yang terlewat. Meskipun mencapai 100% *code coverage*, ini tidak menjamin kode kita bebas dari bug atau kesalahan, karena masih mungkin ada kekurangan dalam *logic* atau *unexpected behaviour*. Pengalaman ini menggarisbawahi pentingnya *unit test* dalam *software development* yang menekankan bahwa setiap *method* dalam *class*, termasuk *getter*, *setter*, dan *constructor*, harus memiliki *unit test* yang relevan untuk meningkatkan keandalan kode.

2. Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the **number of items in the product list**. You decided to create a new Java class similar to the prior functional test suites with **the same setup procedures and instance variables**. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

    Menurut saya, penggunaan *class Java* terpisah yang hanya untuk memeriksa jumlah item dapat mengurangi *cleanliness of the code* karena *functional test* untuk memverifikasi nama produk dan jumlah produk dalam daftar tidak terlalu berbeda, sehingga menghasilkan banyak duplikasi kode. Hal ini tidak hanya membuat kode menjadi kurang rapi, tetapi juga menimbulkan risiko tambahan dalam pemeliharaan. Contohnya setiap perubahan pada sistem, seperti modifikasi template HTML, memaksa perubahan pada kedua *class* secara terpisah. Solusi yang saya sarankan adalah menggabungkan kedua *class* tersebut menjadi satu dan memindahkan baris kode yang berulang ke dalam *method* yang dapat digunakan bersama, seperti *method setup* yang melakukan simulasi pembuatan produk. Hal ini tidak hanya meningkatkan kebersihan dan kualitas kode, tetapi juga memudahkan dalam hal pemeliharaan dan pengujian karena telah memverifikasi banyak aspek setelah produk dibuat tanpa masalah yang berkaitan dengan prinsip *Clean Code* atau *Code Quality*.

## **Tutorial Modul 2: CI/CD & DevOps**

**Reflection**

You have implemented a CI/CD process that automatically runs the test suites, analyzes code quality, and deploys to a PaaS. Try to answer the following questions in order to reflect on your attempt completing the tutorial and exercise.
1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

    Ketika saya melakukan push dengan workflow `sonarcloud.yml`, saya hanya menemukan satu *code quality issue* pada dashboard SonarCloud, yang mungkin menunjukkan bahwa kode saya sudah cukup memenuhi standar kualitas yang ditetapkan oleh SonarCloud. Saya memastikan bahwa semua konfigurasi SonarCloud sesuai dan mencakup semua direktori atau file yang relevan dalam repositori saya, serta memeriksa log pada CI/CD pipeline untuk memastikan tidak ada langkah yang gagal atau error yang menghambat proses berjalannya workflow. Jadi, untuk *code quality issue* pada refleksi nomor 1 ini, mungkin saya hanya perlu memberikan satu issue dari *code quality issue* yang dialami dan bagaimana strategi untuk menyelesaikannya.

    * Redundansi redirect *url* pada ProductController. <br/>
    Solusi untuk memperbaikinya adalah dengan mendefinisikan variable constant untuk return redirect *url* yang akan dipakai berulang.

        ```java
        private static final String REDIRECT_LIST = "redirect:../list";
        ```

2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

    Menurut saya, saat ini saya telah mengimplementasikan CI/CD, yang di mana Continuous Integration mencakup tahapan Code dan Test, sedangkan Continuous Delivery/Deployment menangani bagian Operational dan Review. Kode dalam workflow `ci.yml` akan mengotomatiskan proses *testing* setiap kali saya melakukan *push* atau *pull request* ke suatu *branch*. Kemudian untuk kebersihan dan kualitas kode akan dicek melalui workflow `scorecard.yml` dan `sonarcloud.yml`. Untuk deployment, `Koyeb` sebagai PaaS juga telah mengimplementasikan beberapa CI/CD untuk mengotomatiskan proses deployment setiap kali ada *push* atau *pull request* dari repositori. Sehingga berdasarkan workflow CI/CD yang saya implementasikan, proses Continuous Integration dan Continuous Deployment/Delivery (CI/CD) sepertinya sudah tercapai.
    
## **Tutorial Modul 3: Maintanability & OO Principles**

**Reflection**

Apply the SOLID principles you have learned. You are allowed to modify the source code according to the principles you want to implement. Please answer the following questions: 

1) Explain what principles you apply to your project!

     Berikut ini adalah beberapa prinsip SOLID yang saya terapkan:
    - Single Responsibilty Principle (SRP) <br/>
        Awalnya pada branch `before-solid`, file `ProductController.java` berisi dua class controller yang berbeda, yang bertentangan dengan aturan SRP. Untuk menerapkan prinsip ini, saya memisahkan tanggung jawab kedua class dengan membuat file baru `CarController.java` untuk class `CarController`, yang memastikan setiap class memiliki satu alasan untuk berubah. Saya menerapkan SRP pada `HomePageController`, `ProductController`, dan `CarController`, dengan setiap controller bertanggung jawab atas endpoint berbeda (/, /product, /car), yang memisahkan tanggung jawab berdasarkan area fungsionalitas.
    
    - Liskov Substitution Principle (LSP) <br/>
        `CarController` awalnya merupakan *subclass* dari `ProductController`, meskipun keduanya memiliki perilaku yang berbeda. Ini menunjukkan bahwa `CarController` tidak cocok untuk menjadi *subclass* dari `ProductController` karena tidak memenuhi aturan LSP, yang mengharuskan objek dari *subclass* dapat menggantikan objek dari *superclass* tanpa mengganggu fungsionalitas. Solusi yang saya terapkan adalah menghilangkan hubungan *inheritance* antara kedua controller tersebut dengan menghapus *extends* dan menjadikan `CarController` sebagai class yang independen dan berada di dalam file terpisah serta memastikan kedua class tersebut berdiri sendiri.
        
    - Dependency Inversion Principle (DIP) <br/>
        Pada awalnya, class `CarController` mempunyai *dependency* pada class `CarServiceImpl`. Menurut aturan DIP, seharusnya class tersebut bergantung pada *interface* atau *abstract class*, bukan bergantung langsung pada implementasi di *concrete class*. Untuk menerapkan prinsip ini, saya mengubah class `CarController` untuk bergantung pada *interface* `CarService` bukan *concrete class* `CarServiceImpl` dengan mengubah tipe data dari `CarServiceImpl` menjadi `CarService`.

2) Explain the advantages of applying SOLID principles to your project with examples.

    Keuntungan dari menerapkan prinsip SOLID adalah meningkatkan modularitas, kebersihan kode, dan kemudahan dalam pemeliharaan kode. Menurut saya, hal ini meminimalisir usaha yang diperlukan untuk melakukan modifikasi, karena perubahan besar pada satu bagian kode tidak memengaruhi bagian lain. Sebagai contoh, saya menerapkan Dependency Inversion Principle dalam `CarController` yang bergantung pada *interface* `CarService` bukan implementasi konkret `CarServiceImpl`. Artinya, saya hanya perlu mengubah implementasi pada *layer service* tanpa perlu memodifikasi `CarController`. Jika terjadi perubahan pada *business logic* berarti kita tidak perlu memodifikasi kelas lain yang bergantung padanya. Pendekatan prinsip ini tidak hanya memudahkan pemeliharaan tetapi juga mempermudah *code review* jika berkolaborasi dalam tim dan mengurangi kompleksitas pemahaman kode, sehingga memastikan kode tetap dapat diakses dan mudah dikelola dalam skala yang lebih besar.

3) Explain the disadvantages of not applying SOLID principles to your project with examples.

    Kerugian jika tidak menerapkan prinsip SOLID adalah dapat mengakibatkan berbagai kesulitan dalam pengembangan dan pemeliharaan kode, seperti kode sulit dikelola, sulit menambah fitur, tidak fleksibel, dan apabila menemukan error atau bug akan sulit melacak penyebabnya. Misalnya, jika Single Responsibility Principle (SRP) diabaikan, seperti dalam kasus `CarController` yang tidak dipisahkan dari `ProductController`, dapat menyulitkan kita untuk menemukan dan memahami bagian kode yang spesifik, karena tanggung jawab yang seharusnya terpisah malah digabungkan. 
    Selain itu, tanpa penerapan prinsip SOLID bisa menyebabkan minimnya *reusability* karena ketergantungan yang ketat antar komponen atau *tightly coupled* dan kode akan penuh dengan duplikasi, yang pada akhirnya memperlambat proses pengembangan dan meningkatkan effort untuk modifikasi.