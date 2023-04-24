module Yacht

type Category = 
    | Ones
    | Twos
    | Threes
    | Fours
    | Fives
    | Sixes
    | FullHouse
    | FourOfAKind
    | LittleStraight
    | BigStraight
    | Choice
    | Yacht

type Die =
    | One 
    | Two 
    | Three
    | Four 
    | Five 
    | Six
    static member eval = function
        | One -> 1 
        | Two -> 2 
        | Three -> 3
        | Four -> 4 
        | Five -> 5 
        | Six -> 6

let private diceWithCount dice = 
    dice
    |> List.countBy id
    |> List.sortByDescending snd

let private valueScore value dice =
    let count = 
        dice 
        |> List.filter ((=) value) 
        |> List.length 
    count * (Die.eval value)

let private fullHouseScore dice =
    match diceWithCount dice with
    | [(_, 3); (_, 2)] -> (List.sumBy Die.eval dice)
    | _ -> 0

let private fourOfAKindScore dice =
    match diceWithCount dice with
    | [(d, 4); _] -> (Die.eval d) * 4
    | [(d, 5)]    -> (Die.eval d) * 4
    | _ -> 0

let private littleStraightScore dice =
    if List.sort dice = [One; Two; Three; Four; Five] then 30 else 0

let private bigStraightScore dice =
    if List.sort dice = [Two; Three; Four; Five; Six] then 30 else 0

let private choiceScore dice = 
    List.sumBy Die.eval dice 

let private yachtScore dice = 
    if dice |> List.distinct |> List.length = 1 then 50 else 0

let score category dice = 
    match category with
    | Ones           -> valueScore One dice
    | Twos           -> valueScore Two dice
    | Threes         -> valueScore Three dice
    | Fours          -> valueScore Four dice
    | Fives          -> valueScore Five dice
    | Sixes          -> valueScore Six dice
    | FullHouse      -> fullHouseScore dice
    | FourOfAKind    -> fourOfAKindScore dice
    | LittleStraight -> littleStraightScore dice
    | BigStraight    -> bigStraightScore dice
    | Choice         -> choiceScore dice
    | Yacht          -> yachtScore dice