let allColors = ["red", "green", "blue", "yellow", "olive", "violet", "orange", "black"
    , "teal", "purple", "pink", "brown", "grey"];

let brightColors = ["red", "green", "blue", "yellow", "olive", "orange", "teal"];

function getRandomColor() {
    return allColors[Math.floor(Math.random() * allColors.length)]
}

function getRandomBrightColor() {
    return brightColors[Math.floor(Math.random() * brightColors.length)]
}
