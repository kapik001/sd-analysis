#on start
N = 12
M = 26
averageN = 0
averageM = 0
diffrence = 0
previousDiffrence = 0
averageNData <- c()
averageMData <- c()

#on next day
logger$put('day:')
logger$put(iter)
logger$put('close:')
logger$put(data[[iter]]$close)
if (iter >= M) {

    sum = 0
    for (i in(N - 1) : 0) {
        sum = sum + data[[iter - i]]$close
    }
    averageN = sum / N
    logger$put('averageN:')
    averageNData <- c(averageNData, averageN)
    sum = 0
    for (i in(M - 1) : 0) {
        sum = sum + data[[iter - i]]$close
    }
    averageM = sum / M
    logger$put('averageM:')
    averageMData <- c(averageMData, averageM)

    diffrence = averageN - averageM

    if (iter >= M + 1 && iter < noOfDays) {
        #róźnica leci do góry - kupuj
        if (previousDiffrence > 0 &&
            diffrence > 0 &&
            diffrence > previousDiffrence) {
            buyer$buy(data[[iter]]$close)
            logger$put('buy at:')
            logger$put(data[[iter]]$close)
        }

        #róźnica leci do dołu - sprzedawaj
        if (previousDiffrence < 0 &&
            diffrence < 0 &&
            diffrence < previousDiffrence) {
            buyer$sell(data[[iter]]$close)
            logger$put('sell at:')
            logger$put(data[[iter]]$close)
        }
    }
    previousDiffrence = diffrence
}






#on end
logger$put('XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')
logger$put('N datas: ')
for(a in averageNData){
    logger$put(toString(a))
}

logger$put('XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')
logger$put('M datas: ')
for(a in averageMData){
    logger$put(toString(a))
}
logger$put('XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')
logger$put(buyer$result())


#stockNames
AAPL
MSFT