#on start
loadedData=stockData$load('AAPL', 365)

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

    df.Tomorrow[i-20] <- (loadedData[[i + 1]]$close > loadedData[[i]]$close)
    df.Today[i-20] <- (loadedData[[i]]$close > loadedData[[i - 1]]$close)
    df.X1[i-20] <- (loadedData[[i - 1]]$close > loadedData[[i - 2]]$close)
    df.X2[i-20] <- (loadedData[[i - 2]]$close > loadedData[[i - 3]]$close)
    df.X3[i-20] <- (loadedData[[i - 3]]$close > loadedData[[i - 4]]$close)
    df.X5[i-20] <- (loadedData[[i - 5]]$close > loadedData[[i - 6]]$close)
    df.X10[i-20] <- (loadedData[[i - 10]]$close >  loadedData[[i - 11]]$close)
}


tree <- rpart(Tomorrow ~ Today + X1 + X2 +X3 + X5 + X10, data = df)

loadedData2=stockData$load('MSFT', 365)

df2 <- data.frame(
Today=integer(),
Tomorrow=integer(),
X1=integer(),
X2=integer(),
X3=integer(),
X5=integer(),
X10=integer())

df2.Today <- c()
df2.Tomorrow <- c()
df2.X1 <- c()
df2.X2 <- c()
df2.X3 <- c()
df2.X5 <- c()
df2.X10 <- c()

lengthOfData2=length(loadedData2)
#filling with 'isGrowing'
for (i in 20 : (lengthOfData2 - 2)) {
    df2.Tomorrow[i-20] <- (loadedData2[[i + 1]]$close > loadedData2[[i]]$close)
    df2.Today[i-20] <- (loadedData2[[i]]$close > loadedData2[[i - 1]]$close)
    df2.X1[i-20] <- (loadedData2[[i - 1]]$close > loadedData2[[i - 2]]$close)
    df2.X2[i-20] <- (loadedData2[[i - 2]]$close > loadedData2[[i - 3]]$close)
    df2.X3[i-20] <- (loadedData2[[i - 3]]$close > loadedData2[[i - 4]]$close)
    df2.X5[i-20] <- (loadedData2[[i - 5]]$close > loadedData2[[i - 6]]$close)
    df2.X10[i-20] <- (loadedData2[[i - 10]]$close >  loadedData2[[i - 11]]$close)
}

logger$put(df2.Tomorrow[20])


p <- predict(tree, newdata=df2, type="class")

logger$put(p)


#on next day

#on end

#stockNames
AAPL
MSFT