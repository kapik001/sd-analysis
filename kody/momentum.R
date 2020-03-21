#on start
M = 10
momentum = 0
momentumData <- c()

#on next day
logger$put('day:')
logger$put(iter)
logger$put('close:')
logger$put(data[[iter]]$close)
if (iter >= M) {
    momentum = data[[iter]]$close - data[[iter - M]]$close
    momentumData <- c(momentumData, momentum)
    momentumSize = length(momentumData)
    if (momentumSize > 2) {
        #momentum zaczyna rosnąc gdy jest na dole - kupuj
        if(momentum < 0 && momentumData[momentumSize - 2] < momentum){
            buyer$buy(data[[iter]]$close)
            logger$put('buy at:')
            logger$put(data[[iter]]$close)
        }
        #momentum zaczyna maleć gdy jest na górze - sprzedawaj
        if(momentum > 0 && momentumData[momentumSize - 2] > momentum){
            buyer$sell(data[[iter]]$close)
            logger$put('sell at:')
            logger$put(data[[iter]]$close)
        }
    }
}



#on end
logger$put('XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')
logger$put('M datas: ')
for (m in momentumData) {
    logger$put(toString(m))
}
logger$put('XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')
logger$put(buyer$result())


#stockNames
AAPL
MSFT