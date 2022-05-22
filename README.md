# TABLE OF CONTENTS

- [SQL MODULE](#sql-module)
  * [Abstract](#abstract)
  * [Requirements](#requirements)
  * [BundleListener:](#bundlelistener)
  * [SQLDevListener:](#sqldevlistener)
  * [MetaData:](#metadata)
    + [*meta.xml file tags precedent*](#-metaxml-file-tags-precedent)
    + [*meta.xml precedent*](#-metaxml-precedent)
  * [Data.xml:](#dataxml)
    + [*data.xml file Precedent*](#-dataxml-file-precedent)
  * [Packages:](#packages)





# SQL MODULE

## Abstract:
* The SQL module explains how to use simple DataBase Query implementations.
* This module explains the statically available methods for executing DML, DDL, DQL related database actions.

## Requirements:
This module requires the following maven dependencies.
	
1. javatuples - A tuple is a collection of several elements that may or may not be related to each other. 
	               In other words, tuples can be considered anonymous objects.
2. HikariCP - HikariCP is a solid high-performance JDBC connection pool. 
		     A connection pool is a cache of database connections maintained so that the connections can be reused 
		     when future requests to the database are required.
3. PostgreSQL - PostgreSQL is an advanced, enterprise-class open-source relational database that 
	               supports both SQL (relational) and JSON (non-relational) querying.	
	
## BundleListener:
	A BundleEvent listener. When a BundleEvent is fired, it is asynchronously delivered to a BundleListener. BundleListeners are 
	called with a BundleEvent object when a bundle has been installed, resolved, started, stopped, updated, unresolved, or uninstalled.
	
## SQLDevListener:
	Utilizing the bundle lifecycle, it will perform the actions like metadata parsing, etc.
	The class explains the loading, processing, updating and populating the meta and data xml files. 
	Make use of this we can write meta file history and fetch meta file history. We can see this in snapshots location of our system. 
	
## MetaData:
	The meta.xml file to load and populate the metadata to fetch and configure the table definitions, and initialize the sequence 
	generation of primary keys. We set up the metadata with all the essential things like table name, columns, and constraints 
	to configure the table in the database. 
	
### *meta.xml file tags precedent*
	
Table:
Table name and type of the table like common or org dependent table are provided here.
> ``<table name="TableName" type="11">...
	  ...
	     </table>``
		 
Column:
Coulmn name of the table, its data type, whether its nullable or not, maximum length, default value for the column are provided here. 
> ``<columns>
                <column name="NAME" data-type="CHAR" max-length="30" nullable="false" default-value="1"/>
            </columns>``

Primary key:
Column to be set as primary key of the table should be here. 
> ``<primary-key name="Table_PK" column="ID" sequence-generator="TablePk.ID" />``
            
Foreign key:
Column to be set as foreign-key of the table, reference table and its reference column are provided here with constraints. 
> ``<foreign-keys> <foreign-key name="Table_FK" reference-table="Table1" local-column="Table1_ID" 
		reference-column="ID" constraint="ON-DELETE-CASCADE" /> </foreign-keys>``
			
Index key:		
Column to be set as index key of the table should be here. 
> ``<indexes> <index name="Index_Id">
             <index-column>TABLE_NAME</index-column>
               </index> </indexes>``
			
Unique key:
Column to be set as unique key of the table should be here. 
> ``<unique-keys> <unique-key name="UniqueKey_UK">
         <unique-key-column>ID</unique-key-column>
               </unique-key> </unique-keys``
		
### *meta.xml precedent*
* A sample meta.xml is shown for reference 
	

	       <table name="Sample" type="11">
            <columns>
                <column name="ID" data-type="BIGINT"/>
                <column name="NAME" data-type="CHAR" nullable="false" />
		<column name="COMPANY_ID" data-type="BIGINT" default-value="1">
                <column name="PROVIDER_ID" data-type="CHAR" nullable="false" />
            </columns>
            <primary-key name="SAMPLE_PK" column="ID" sequence-generator="SAMPLE.ID" />
	    <foreign-keys>
                <foreign-key name="SAMPLE_ID_FK" reference-table="COMPANY"
                             local-column="COMPANY_ID" reference-column="ID" constraint="ON-DELETE-CASCADE"/>
            </foreign-keys>
	    <unique-keys>
                <unique-key name="SAMPLE_UK">
                    <unique-key-column>PROVIDER_ID</unique-key-column>
                </unique-key>
            </unique-keys>
            <indexes>
                <index name="SAMPLE_Idx">
                    <index-column>NAME</index-column>
                </index>
            </indexes>
        </table>
	
## Data.xml:
	In this we can statically enter an entity for the table.
	
> ``<MCMFieldSQLTable ID="MCMFieldSQLTable:ID:CO:6" TABLE_NAME="MCOLifeCycleStage"/>``



### *data.xml file Precedent*

 <MCMFieldSQLTable ID="MCMFieldSQLTable:ID:CO:6" TABLE_NAME="MCOLifeCycleStage"/>

        <MCMDataProvider ID="MCMDataProvider:ID:CO:50" TYPE="1" NAME="COMPANY_LIST">
            <MCMFields ID="MCMFields:ID:CO:50" NAME="id" DISPLAY_NAME="i18n.contact.company.id.display.name" 
		       PRIMARY="true" SORT="1" SEARCHABLE="false" ORDER="5" HIDDEN="true">
                <MCMFieldSQLSource ID="MCMFieldSQLSource:ID:CO:50" TABLE_ID="MCMFieldSQLTable:ID:CO:1" COLUMN="ID" TYPE="" FLAG=""/>
            </MCMFields>
            <MCMFields ID="MCMFields:ID:CO:100" NAME="name" DISPLAY_NAME="i18n.contact.company.display.name" SORT="1" 
		       SEARCHABLE="true" ORDER="10" HIDDEN="false">
                <MCMFieldSQLSource ID="MCMFieldSQLSource:ID:CO:100" TABLE_ID="MCMFieldSQLTable:ID:CO:1" COLUMN="NAME" TYPE="" FLAG=""/>
            </MCMFields>
        </MCMDataProvider>``
       
	
## Packages:
	- api - It provides the interfaces for data containers, queries, constraints, and clauses. 
	- internal - It provides the implementations of api package.
	- resource - Auto generataed table content class file. 
	- update dll - The interface for DDL-related actions.

1. api package:
* dml - Provides interfaces for dml related queries.
* ds - Provides interfaces for admindatastore, orgdatastore, readable and writable datastore.
* listener - Provides interfaces for listeners like row added, row updated and row deleted listeners.
* meta - Provides classes for column definition, constraints definitions, table definition and database supported data types.
* sequence - Provides interfaces for sequence generator.

2. internal package:
* data - Provides implementations for data container.
* dml - Provides implementations for dml related queries.
* ds - Provides implementations for readable and writable datastore, admindatastore and orgdatastore.
* handler - Provides abstract methods for all DML and DDL related queries.  
* listener - Provides implementations for listeners like row added, row updated and row deleted listeners.
* meta - Provides classes for metadata handler and meta cache.
* parser - Provides implementations for metadata parser, constraints resolver, table definition loader, etc.
* pgsql - Provides services for PostGreSql and its related functionalities.
* sequence - Provides implementations for batch and sequence generations.
* status - Provides SQL related error codes to use. 
* update - Provides interfaces and implementations for DMLUpdate and DDLUpdate manager.











