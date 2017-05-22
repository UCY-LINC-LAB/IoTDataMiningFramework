# IoTDataMiningFramework
----

## Usage
1. Generate IoTDataMiningFramework.jar file.
2. Deploy and import IoTDataMiningFramework.jar file in eclipse project.
3. Make sure you have the right pom file with dependencies.
5. Extend the existing models to create specialized ones

----
## API


#### Interface DataResource
##### Package: Framework.resource

Modifier and Type	          |          Method and Description
----------------------------|--------------------------------
  void	| closeFile() 
  void	| getLine(String filename, String delimiter, int num) 
  void	| openFile(String filename) 
  void	| readAll(String filename, String delimiter) 
  ArrayList of String	| readData(ArrayList of DataPoint  data, String delimiter, int[] columns, int lblCol, DataPointType type) 
  ArrayList of DataPoint | readData(String delimiter, int column, DataPointType type) 
  void	| readSome(String filename, String delimiter, int limit)
  



#### Abstract Class DataPoint
##### Package: Framework.data.DataPoint

Modifier and Type	          |          Method and Description
----------------------------|--------------------------------
  abstract Label	          |   getLabel()  Assigned label of training data.
  String	                  |   getName() 
  int	                      |   getSequenceID() 
  java.sql.Timestamp	      |   getTimestamp() 
  DataPointType	            |   getType() 
  java.lang.Object	        |   getValue() 
  abstract java.lang.Object	|   getValue(java.lang.String column) Get sample data value from specified column.
  boolean	                  |   has(Feature feature)
  void	                    |   setName(java.lang.String name) 
  void	                    |   setSequenceID(int seq) 
  void	                    |   	setTimestamp(java.sql.Timestamp timestamp) 
  void	                    |   	setType(DataPointType type) 
  
  
  
#### Interface TimeSeries
##### Package: Framework.algorithm.Timeseries

Modifier and Type	          |          Method and Description
----------------------------|--------------------------------
  double	| addDataPoint(T dp) 
  void	| calculateStdDev(T dp) 
  double	| getAverage() 
  double	| getStdDev() 
  void	| reset() 
  
  
  
#### Abstract Class DecisionTree
##### Package: Framework.algorithm.classification.decisiontree.DecisionTree

Modifier and Type	          |          Method and Description
----------------------------|--------------------------------
  Label | classify(DataPoint dataSample)   Classify dataSample.
  Node	| getRoot()       Get root.
  void	| printSubtree(Node node) 
  void	| printTree() 
  void	| train(List of DataPoint trainingData, List of Feature features)       Trains tree on training data for provided     
  
  
  
#### Interface ImpurityCalculationMethod
##### Package: Framework.algorithm.classification.decisiontree.impurity

Modifier and Type	          |          Method and Description
----------------------------|--------------------------------
  double  |	calculateImpurity(List of DataPoint splitData)    Calculates impurity value.
  default | double	getEmpiricalProbability(List of DataPoint splitData, Label positive, Label negative)  Calculate and return empirical probability of positive class. 
  
  

#### Abstract Class Label
##### Package: Framework.algorithm.classification.decisiontree.label

Modifier and Type	          |          Method and Description
----------------------------|--------------------------------
  abstract boolean	| equals(Object o)    Force overriding equals.
  abstract String	|  getName() 
  abstract String	|  getPrintValue()   Label value used to print to predictions output.
  abstract int  |	hashCode()      Force overriding hashCode.
  
  
