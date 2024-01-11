fetch("/books")
    .then(function (respone) {
        return respone.json();
    })
    .then(function (books) {
        let placeholder = document.querySelector("#data");
        let out = "";
        for (let book of books) {
            out += `
         <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.authorDto.firstName}</td>
            <td>${book.authorDto.lastName}</td>
            <td>${book.genreDto.name}</td>        
            <td>
            <a class="btn-link" href="/books?id=${book.id}">
                <button>Edit</button>
            </a>
            <form action="/books?id=${book.id}" method="post">
            <input type="hidden" name="_method" value="delete">
                <button type="submit" class="btn-link">Delete</button>
            </form>
            </td>
         </tr>
      `;
        }
        placeholder.innerHTML = out;
    })