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

    df.Tomorrow[i-20] <- if (loadedData[[i + 1]]$close > loadedData[[i]]$close)  1 else 0
    df.Today[i-20] <- if(loadedData[[i]]$close > loadedData[[i - 1]]$close)  1 else 0
    df.X1[i-20] <-if (loadedData[[i - 1]]$close > loadedData[[i - 2]]$close)  1 else 0
    df.X2[i-20] <-if (loadedData[[i - 2]]$close > loadedData[[i - 3]]$close)  1 else 0
    df.X3[i-20] <-if (loadedData[[i - 3]]$close > loadedData[[i - 4]]$close)  1 else 0
    df.X5[i-20] <-if (loadedData[[i - 5]]$close > loadedData[[i - 6]]$close)  1 else 0
    df.X10[i-20] <-if (loadedData[[i - 10]]$close >  loadedData[[i - 11]]$close)  1 else 0
}

for(i in 0 : 400){
    logger$put(df.X5[i])
}

mylogit <- glm(data.frame(df.Today, df.X1, df.X2, df.X3, df.X5, df.X10), df.Tomorrow,  family = binomial())

#tree <- rpart(formula = Tomorrow ~  Today + X1 + X2 + X3 + X5 + X10, data = df)

loadedData2=stockData$load('MSFT', 365)

df2 <- data.frame(
Tomorrow=integer(),
Today=integer(),
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

t = c()

lengthOfData2=length(loadedData2)
#filling with 'isGrowing'
for (i in 20 : (lengthOfData2 - 2)) {
    df2.Tomorrow[i-20] <- if (loadedData2[[i + 1]]$close > loadedData2[[i]]$close)  1 else 0
    df2.Today[i-20] <- if(loadedData2[[i]]$close > loadedData2[[i - 1]]$close)  1 else 0
    df2.X1[i-20] <-if (loadedData2[[i - 1]]$close > loadedData2[[i - 2]]$close)  1 else 0
    df2.X2[i-20] <-if (loadedData2[[i - 2]]$close > loadedData2[[i - 3]]$close)  1 else 0
    df2.X3[i-20] <-if (loadedData2[[i - 3]]$close > loadedData2[[i - 4]]$close)  1 else 0
    df2.X5[i-20] <-if (loadedData2[[i - 5]]$close > loadedData2[[i - 6]]$close)  1 else 0
    df2.X10[i-20] <-if (loadedData2[[i - 10]]$close >  loadedData2[[i - 11]]$close)  1 else 0
}

#p <- predict(tree, data.frame(Today = df2.Today, X1 = df2.X1, X2 = df2.X2, X3 =  df2.X3, X5 = df2.X5, X10 = df2.X10), type="class")


p <- predict(mylogit, data.frame(df2.Today, df.X1 = df2.X1, df.X2 = df2.X2,df.X3 =  df2.X3, df.X5 = df2.X5, df.X10 = df2.X10), type="response")

for(i in 0: 300){
    logger$put(p[i])
}

