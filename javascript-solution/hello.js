//Problem 4
function isAnagram(str1, str2) {
    // Convertir las cadenas de texto a arrays y ordenarlos
    const arr1 = str1.split("").sort();
    const arr2 = str2.split("").sort();

    // Verificar si los arrays son iguales
    return JSON.stringify(arr1) === JSON.stringify(arr2);
}

console.log("anagram test 1", isAnagram("roma", "amor"));
console.log("anagram test 2", isAnagram("robert", "trebor"));
console.log("anagram test 3", isAnagram("si", "no"));

//Problem 5
function findCommonElements(lists) {
    // Inicializar un objeto para almacenar los elementos comunes
    const commonElements = {};

    // Iterar sobre cada sub-lista
    for (let i = 0; i < lists.length; i++) {
        const list = lists[i];

        // Iterar sobre cada elemento de la sub-lista
        for (let j = 0; j < list.length; j++) {
            const element = list[j];

            // Si el elemento ya está en el objeto, incrementar su contador
            if (commonElements[element]) {
                commonElements[element]++;
            } else {
                // Si no está en el objeto, inicializar su contador en 1
                commonElements[element] = 1;
            }
        }
    }

    // Filtrar los elementos que aparecen en todas las sub-listas
    const commonList = Object.keys(commonElements).filter(element => commonElements[element] === lists.length);

    return commonList;
}

const lists1 = [[1, 2, 3], [2, 3, 4], [3, 2, 4, 5]];
const lists2 = [[1, 2, 3, 4, 6], [1, 3, 4], [3, 2, 4, 5, 1]];


console.log("findCommonElements test 1", findCommonElements(lists1));
console.log("findCommonElements test 2", findCommonElements(lists2));

//Problem 6
function merge(left, right) {
    const result = [];
    let i = 0;
    let j = 0;

    while (i < left.length && j < right.length) {
        if (left[i] < right[j]) {
            result.push(left[i]);
            i++;
        } else {
            result.push(right[j]);
            j++;
        }
    }

    return [...result, ...left.slice(i), ...right.slice(j)];
}

function mergeSort(arr) {
    if (arr.length <= 1) {
        return arr;
    }

    const middle = Math.floor(arr.length / 2);
    const left = arr.slice(0, middle);
    const right = arr.slice(middle);

    return merge(mergeSort(left), mergeSort(right));
}

const arr = [5, 2, 4, 6, 1, 3];
console.log("mergeSort test 1", mergeSort(arr));