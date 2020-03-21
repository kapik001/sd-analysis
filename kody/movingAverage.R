#on start
N = 10
average = 0
previousAverage = 0
closeData <- c()
averageData <- c()

#on next day
logger$put('day:')
logger$put(iter)
logger$put('close:')
closeData <- c(closeData, data[[iter]]$close)
if(iter >= N){

    sum = 0
    for(i in (N-1):0){
        sum = sum + data[[iter - i]]$close
    }
    average = sum/N
    logger$put('average:')
    averageData <- c(averageData, average)
    if(iter >= N + 1 && iter < noOfDays){
        #cena przecina od góry - sprzedawać
        if(data[[iter - 1]]$close > previousAverage && data[[iter]]$close < average){
            buyer$buy(data[[iter]]$close)
            logger$put('buy at:')
            logger$put(data[[iter]]$close)
        }
        #cena przecina od dołu - kupować
        if(data[[iter - 1]]$close < previousAverage && data[[iter]]$close > average){
            buyer$sell(data[[iter]]$close)
            logger$put('sell at:')
            logger$put(data[[iter]]$close)
        }
    }
    previousAverage = average
}




#on end
logger$put(' ')

logger$put('close datas: ')
for(c in closeData){
    logger$put(toString(c))
}

logger$put(' ')

logger$put('avera datas: ')
for(a in averageData){
    logger$put(toString(a))
}
buyer$sell(data[[iter]]$close)
logger$put(buyer$result())


#stockNames
AAPL
MSFT