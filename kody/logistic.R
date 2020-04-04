#run before
loadedData=stockData$load('AAPL', 365)

#on start
probData <- c()
closeData <- c()

df <- data.frame(
Today=integer(),
Tomorrow=integer(),
X1=integer(),
X2=integer(),
X3=integer(),
X5=integer(),
X10=integer())

df.Today <- c()
df.Tomorrow <- c()
df.X1 <- c()
df.X2 <- c()
df.X3 <- c()
df.X5 <- c()
df.X10 <- c()


lengthOfData=length(loadedData)
#filling with 'isGrowing'
for (i in 20 : (lengthOfData - 2)) {

    df.Tomorrow[i-20] <- if (loadedData[[i + 1]]$close > loadedData[[i]]$close)  1 else 0
    df.Today[i-20] <- if(loadedData[[i]]$close > loadedData[[i - 1]]$close)  1 else 0
    df.X1[i-20] <-if (loadedData[[i - 1]]$close > loadedData[[i - 2]]$close)  1 else 0
    df.X2[i-20] <-if (loadedData[[i - 2]]$close > loadedData[[i - 3]]$close)  1 else 0
    df.X3[i-20] <-if (loadedData[[i - 3]]$close > loadedData[[i - 4]]$close)  1 else 0
    df.X5[i-20] <-if (loadedData[[i - 5]]$close > loadedData[[i - 6]]$close)  1 else 0
    df.X10[i-20] <-if (loadedData[[i - 10]]$close >  loadedData[[i - 11]]$close)  1 else 0
}


mylogit <- glm(data.frame(df.Today, df.X1, df.X2, df.X3, df.X5, df.X10), df.Tomorrow,  family = binomial())

#on next
logger$put('day:')
logger$put(iter)
logger$put('close:')
logger$put(data[[iter]]$close)
closeData <- c(closeData, data[[iter]]$close)
if (iter >= 20) {
    Today <- if(data[[iter]]$close > data[[iter - 1]]$close)  1 else 0
    X1 <- if (data[[iter - 1]]$close > data[[iter - 2]]$close)  1 else 0
    X2 <- if (data[[iter - 2]]$close > data[[iter - 3]]$close)  1 else 0
    X3 <- if (data[[iter - 3]]$close > data[[iter - 4]]$close)  1 else 0
    X5 <- if (data[[iter - 5]]$close > data[[iter - 6]]$close)  1 else 0
    X10 <- if (data[[iter - 10]]$close >  data[[iter - 11]]$close)  1 else 0
    p <- predict(mylogit, data.frame(df.Today = Today, df.X1 = X1, df.X2 = X2, df.X3 =  X3, df.X5 = X5, df.X10 = X10), type="response")
    probData = c(probData, p)
    if(p > 0.5) {
        buyer$buy(data[[iter]]$close)
        logger$put('buy at:')
        logger$put(data[[iter]]$close)
    } else {
        buyer$sell(data[[iter]]$close)
        logger$put('sell at:')
        logger$put(data[[iter]]$close)
    }
}
#on end
logger$put('XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')
logger$put('close datas: ')
for(c in closeData){
    logger$put(toString(c))
}
logger$put('XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')
logger$put('P datas: ')
for (m in probData) {
    logger$put(toString(m))
}
logger$put('XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX')
logger$put(buyer$result(data[[iter]]$close))


