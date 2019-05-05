let allColors = ["red", "green", "blue", "yellow", "olive", "violet", "orange", "black"
    , "teal", "purple", "pink", "brown", "grey"];

function getRandomColor() {
    return allColors[Math.floor(Math.random() * allColors.length)]
}

function getColorNameForFindingWithAge(age) {
    return age <= 7 ? 'red' : age > 30 ? 'blue' : 'yellow';
}