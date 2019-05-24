# README #

# GRPC over REST #


**Description:**  

  Invoking lamda functions using GRPC and REST.
  
**Explain/Notes:**

    - Added protoc.sbt inside project directory and my lamda.proto file inside "protobuf" directory.
    - created 4 RPC calls for proto (Add, Div, Mul, Sub)
    - ScalaPB compiler compiles the lamda.proto into respective GRPC classes within target\scala-2.12\src_managed\main\lamda
    
    
    
**Execution:**  
  
   After a long struggle, I was able to run my GRPC over REST via grpcGateway provided by "beyondthelines". 
 
   ![Snapshot for GRPC Call](jars/GRPC_OVER_REST.PNG)
  
  
- In the query shown the variable varA and varB can take any integer value. The function mul can be replaced by either add, div,mul or sub (basic arithematic operations).

- The compiled jar is within '/jars' folder. The same is being run in the 'FIG-1' which exposes 2 ports:
   
    - Port 8980 for GRPC calls over HTTP2 which cannot be opened via "GET" in browser. This is done in 'FIG-3' via running GRPC client to communicate and get the result of all 4 operations.
    - Port 8981 for GET/POST calls via Browser by bundling HTTP1 over it as shown in 'FIG-2'. This is done by running a separate thread which exposes this port and acts as a gateway for GRPC calls.  

 
 **Deployment on AWS Lamda**
     
     Link: https://3ghwd9429f.execute-api.us-east-1.amazonaws.com/prod/mul?varA=50&varB=10
     
 
That's It ! :) 