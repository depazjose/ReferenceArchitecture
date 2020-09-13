# SpringBoot_VSCode 
# Hexagonal architecture

This is a Java microservice project template, using hexagonal architecture as proposal for new projects. 


The projects has the following structure:


**../applications** <br />
**&nbsp;../configuration** <br />
&nbsp;&nbsp;UseCaseConfig.java <br />
&nbsp;&nbsp;SwaggerConfiguration.java <br />
../domain <br />
&nbsp;../model <br />
&nbsp;&nbsp;../gateway <br />
&nbsp;&nbsp;&nbsp;BookRepository.java <br />
&nbsp;&nbsp;Book.java <br />
../usecase <br />
&nbsp;BookUseCase.java <br />
../infrastructure <br />
&nbsp;../adapters <br />
&nbsp;&nbsp;../h2repository <br />
&nbsp;&nbsp;&nbsp;BookData.java <br />
&nbsp;&nbsp;&nbsp;BookDataRepository.java <br />
&nbsp;&nbsp;&nbsp;BookRepositoryAdapter.java <br />
&nbsp;../entrypoints <br />
&nbsp;&nbsp;../receivers <br />
&nbsp;&nbsp;&nbsp;../book <br />
&nbsp;&nbsp;&nbsp;&nbsp;../dto <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BookRequest.java <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BookResponse.java <br />
&nbsp;&nbsp;&nbsp;&nbsp;BookController.java <br />
**MainApplication.java** <br />
