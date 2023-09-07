async function autoHint(idId, idName, parentId) {

    const inputId = document.getElementById(idId);
    const inputEl = document.getElementById(idName);
    const inputVal = inputEl.value
    if(inputVal.length === 0) {
        removeAutoCompleteDropDown();
        return;
    }

    switch (idName){
        case 'speciesListName': {
            var species= await fetch("/species/list?firstLetters=" + inputVal);
            var AsData = await species.json();
            break;
        }
        case 'associatedSpeciesListName':{
            var associatedSpecies= await fetch("/associatedSpecies/list?firstLetters=" + inputVal);
            var AsData = await associatedSpecies.json();
            break;
        }
        case 'districtListName':{
            var district= await fetch("/district/list?firstLetters=" + inputVal);
            var AsData = await district.json();
            break;
        }
    }

    console.log(AsData);
    removeAutoCompleteDropDown();
    createAutoCompleteDropDown(inputId, AsData, parentId,inputEl);
}

function createAutoCompleteDropDown(inputId, AsData,parentId,inputEl) {
    const listEl = document.createElement("ul");
    listEl.id = "autoComplete-list";//что бы потом его удалять из removeAutoCompleteDropDown()
    AsData.forEach(d => {
        const listItem = document.createElement("li");
        const dataButton = document.createElement("button");
        dataButton.innerHTML = d.value;
        dataButton.className="btn btn-light";
        dataButton.addEventListener("click", function() {
            inputId.value = d.id;
            inputEl.value = dataButton.innerHTML;
            removeAutoCompleteDropDown();
        });
        listItem.appendChild(dataButton);
        listEl.style.position="fixed";
        listEl.style.listStyleType="none";
        listEl.appendChild(listItem);
    });
    document.querySelector("#" + parentId).appendChild(listEl);
}
function removeAutoCompleteDropDown(){
    const listE1 = document.querySelector("#autoComplete-list");
    if(listE1) listE1.remove();
}
