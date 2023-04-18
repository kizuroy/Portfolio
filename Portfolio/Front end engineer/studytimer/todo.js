let ToDoButton = document.getElementById("addToDo");
let toDoContainer = document.getElementById('toDoCaontainer');
let inputFeild = document.getElementById('inputFeeld');

ToDoButton.addEventListener('click',function (){
    var paragraph = document.createElement('p')
    paragraph.classList.add('tasks')
    paragraph.innerText = inputFeild.value;
    toDoContainer.appendChild(paragraph);
    inputFeild.value = '';
    paragraph.addEventListener("click", function (){
        if (paragraph.style.textDecoration === "line-through"){
            toDoContainer.removeChild(paragraph);
        }
    });

    paragraph.addEventListener("click", function (){
        paragraph.style.textDecoration = "line-through"
    });

});

inputFeild.addEventListener('keypress', function (event){
    if(event.key === "Enter"){
        event.preventDefault();
        document.getElementById('addToDo').click();
    }
});