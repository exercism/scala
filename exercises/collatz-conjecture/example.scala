object CollatzConjecture {

  def isEven( v:Int ) : Boolean = v % 2 == 0

  def collatz_h(n: Int, acc: Int) : Int ={
    if (n == 1){
      acc
    }else if (isEven(n)){
      collatz_h(n/2, acc+1)
    }else{
      collatz_h(n*3 + 1, acc+1)}
  }

  def collatz(n: Int) : Int = collatz_h(n, 0)
}
