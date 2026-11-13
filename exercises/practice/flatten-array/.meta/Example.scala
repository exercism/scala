object FlattenArray:
  def flatten(list: List[Any]): List[Any] = list.flatMap:
    case null            => Nil
    case nested: List[?] => flatten(nested)
    case value           => List(value)
