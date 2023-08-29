export class RestService<T> {
    private _path = '';

    async fetchData(url: string, method: string, payload:any) {
        // TODO: refactor to not repeat
        let parameters = (method == 'GET' ? 
        {
            method: method, // 'POST', 'GET', 'PUT', 'DELETE'
            headers: {
                'Content-Type': 'application/json'
            }
        } : 
        {
            method: method, // 'POST', 'GET', 'PUT', 'DELETE'
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })

        try {
            const response = await fetch(url, parameters);
            const data = await response.json();

            return data;
        } catch (error) {
            console.log('*** Error!' + error);
            return {};
        }
    }    

    async callUrl(method:string, payload:any, attributes:string) {
        const url = this._path + (attributes ? '/' + attributes : '')

        // Make the API call
        const response = await this.fetchData(url, method, payload);
        return response;
    }

    constructor(path:string) {
        this._path = path;
    }

    async create(object:T): Promise<T> {
         const newObject = await this.callUrl('POST', object, '');
         return newObject;
    }

    async update(id:number, object:T): Promise<T> {
        const newObject = await this.callUrl('PUT', object, id.toString());
        return newObject;
    }

    async getAll(): Promise<T[]> {
        const objectsString = await this.callUrl('GET', {}, '');
        const objects: T[] = objectsString.map((item: any) => item as T);
        return objects;
    }

    async getById(id:number): Promise<T> {
        const objectString = await this.callUrl('GET', {}, id.toString());
        return objectString as T;
    }

    async delete(id:number): Promise<boolean> {
         const result = await this.callUrl('DELETE', {}, id.toString());
         return !(result === '{}');
    }
}