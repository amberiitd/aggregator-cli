# Aggregator CLI

The Aggregator CLI is a command-line utility designed to aggregate user activity events and generate daily summary reports. It supports both one-time aggregation and real-time updates as new events are added to the dataset.
NOTE: Enable real time aggregation using `--update` cli option. In real time aggreation option only the events with timestamp greater than the modified timestamp of output file will be processed.

## Features

- Aggregate user activity events from a JSON file.
- Generate daily summary reports.
- Support real-time updates for continuous aggregation.

## Usage

### Installation

1. Ensure you have Java installed on your system.
2. Make script file `aggregate_events` executable:
```bash
   chmod +x aggregator
 ```

## CLI command argument Options
- -i, --input: Path to the input JSON file.
- -o, --output: Path to the output JSON file.
- --update[Optional]: Enable real-time update mode.

## One-Time Aggregation
```bash
  ./aggregator -i input.json -o output.json
```
## Real-Time Aggregation
```bash
  ./aggregator -i input.json -o output.json
```