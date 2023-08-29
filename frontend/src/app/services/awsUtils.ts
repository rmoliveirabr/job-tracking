import * as AWS from 'aws-sdk';

export class AWSUtils<T> {
    docClient?: AWS.DynamoDB.DocumentClient;

    constructor() { 
        // setup AWS
        AWS.config.update({
            accessKeyId: 'TBD',
            secretAccessKey: 'TBD',
            region: 'sa-east-1'
        });
        this.docClient = new AWS.DynamoDB.DocumentClient();
        console.log('Setup AWS');
    }

    async getItems(table: string): Promise<T[]> {
        const params = {
            TableName: table,
        };

        const genericItems = await this.docClient?.scan(params).promise();
        const typedItems:T[] = [];
        genericItems?.Items?.forEach((item: any) => typedItems.push(<T>item));
        return typedItems;
    }

    async getItem(table:string, id:string): Promise<T> {
        const params = {
          TableName: table,
          Key: {
            id: id
          }
        };
        
        let item = await this.docClient?.get(params).promise();
        return <T>item?.Item;
    }    
    async deleteItem(table:string, id:string): Promise<boolean> {
        const params = {
          TableName: table,
          Key: {
            id: id
          }
        };
        
        await this.docClient?.delete(params).promise();
        return true;
    } 

    async upsertItem<T extends AWS.DynamoDB.DocumentClient.PutItemInputAttributeMap>(table:string, item:T): Promise<boolean> {
        const params = {
          TableName: table,
          Item: item
        };
    
        await this.docClient?.put(params).promise();
        return true;
    }
}