# Markdown Tips and Trick

> ### Mermaid GeoJSON -> Location In MD
>* https://github.blog/changelog/2022-03-17-mermaid-topojson-geojson-and-ascii-stl-diagrams-are-now-supported-in-markdown-and-as-files/

### Here's a geoJSON map in markdown

```geojson
{
  "type": "Polygon",
  "coordinates": [
      [
          [-90,30],
          [-90,35],
          [-90,35],
          [-85,35],
          [-85,30]
      ]
  ]
}
```

> ### Mermaid JS
> Git repo & docs
>* https://github.com/mermaid-js/mermaid

flowchart LR

A[Hard] -->|Text| B(Round)
B --> C{Decision}
C -->|One| D[Result 1]
C -->|Two| E[Result 2]
