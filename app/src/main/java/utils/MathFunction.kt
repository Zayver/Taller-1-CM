package utils

class MathFunction {
    companion object{
        fun fibonacciSequence(bound: Int): ArrayList<Long>{
            val arr = ArrayList<Long>()
            var x = 1L
            var y = 1L
            var z: Long
            arr.add(y)
            for(i in 1 until bound){
                arr.add(y)
                z = x + y
                x = y
                y = z
            }
            return arr
        }
        fun factorialWithStr(bound:Int): Pair<String, Long>{
            var str = "1"
            var res = 1L
            for(i in 2..bound){
                str+="*$i"
                res*=i
            }
            return Pair(str, res)

        }
        //fun factorial(num: Int) = (2..num).fold(1, Int::times)
    }
}
